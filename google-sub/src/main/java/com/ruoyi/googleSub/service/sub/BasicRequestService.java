package com.ruoyi.googleSub.service.sub;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.SecurityUtils;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.api.services.androidpublisher.model.SubscriptionPurchase;

import com.ruoyi.googleSub.domain.dto.GooglePayDto;
import com.ruoyi.googleSub.domain.dto.SubSecretDto;
import com.ruoyi.googleSub.domain.entity.ProductsInfo;
import com.ruoyi.googleSub.domain.entity.SubscriptionInfo;
import com.ruoyi.googleSub.service.VipProductsService;
import com.ruoyi.googleSub.service.VipSubscriptionsService;
import com.ruoyi.googleSub.utils.HttpRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: 基础订阅服务
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/18 8:51
 */
@Slf4j
@Component
public class BasicRequestService {

    @Autowired
    SubSecretDto subSecretDto;

    @Autowired
    HttpRequestUtil httpRequestUtil;


    @Autowired
    VipProductsService vipProductsService;

    @Autowired
    VipSubscriptionsService vipSubscriptionsService;


    public Map<String, String> subscriptionsGoodsCheck(GooglePayDto googlePayDto) {
        SubscriptionInfo subscriptionInfo = getSubscriptionInfo(googlePayDto);
        Map<String, String> res = new HashMap<>();
        String orderId = subscriptionInfo.getOrderId();
        vipSubscriptionsService.dealSubscriptionsData(googlePayDto, subscriptionInfo);
        if (subscriptionInfo.getPaymentState() != null && subscriptionInfo.getPaymentState() == 1 && subscriptionInfo.getAcknowledgementState() == 1) {
            res.put("status", "1");
            res.put("orderId", orderId);
            return res;
        }
        res.put("status", "0");
        res.put("orderId", orderId);
        return res;
    }


    public Map<String, String> productsGoodsCheck(GooglePayDto googlePayDto) {

        ProductsInfo productsInfo = getProductsInfo(googlePayDto);
        Map<String, String> res = new HashMap<>();
        String orderId = productsInfo.getOrderId();

        vipProductsService.dealProductsData(googlePayDto, productsInfo);

        if (productsInfo.getAcknowledgementState() == 1 && productsInfo.getPurchaseState() == 0) {
            res.put("status", "1");
            res.put("orderId", orderId);
            return res;
        } else if (productsInfo.getPurchaseState() == 1) {
            res.put("status", "0");
            res.put("orderId", orderId);
            return res;
        }
        return res;
    }

    public SubscriptionInfo getSubscriptionInfo(GooglePayDto googlePayDto) {
        String accessKey = httpRequestUtil.grantAccessKey(subSecretDto.getGrantType(), subSecretDto.getClientId(), subSecretDto.getRefreshToken(), subSecretDto.getClientSecret());

        SubscriptionInfo subscriptionInfo = new SubscriptionInfo();
        try {
            subscriptionInfo = httpRequestUtil.getSubscriptionInfo(accessKey, googlePayDto);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return subscriptionInfo;
    }

    public ProductsInfo getProductsInfo(GooglePayDto googlePayDto) {
        String accessKey = httpRequestUtil.grantAccessKey(subSecretDto.getGrantType(), subSecretDto.getClientId(), subSecretDto.getRefreshToken(), subSecretDto.getClientSecret());

        try {
            return httpRequestUtil.getProductsInfo(accessKey, googlePayDto);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void testSDK(GooglePayDto googlePayDto) {

        SubscriptionPurchase subscriptionPurchase = new SubscriptionPurchase();

//        Goo   gleSubscribeCallback
        try {

            ClassPathResource classPathResource = new ClassPathResource("googleP12");
            InputStream input = classPathResource.getInputStream();
            HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
            PrivateKey privateKey = SecurityUtils.loadPrivateKeyFromKeyStore(
                    SecurityUtils.getPkcs12KeyStore(),
                    input, //文件流
                    "notasecret", "privatekey", "notasecret");
            GoogleCredential credential = new GoogleCredential.Builder()
                    .setTransport(transport).setJsonFactory(JacksonFactory.getDefaultInstance())
                    .setServiceAccountId("serviceAccountEmail") // 替换掉serviceAccountEmail
                    .setServiceAccountScopes(AndroidPublisherScopes.all())
                    .setServiceAccountPrivateKey(privateKey).build();
            AndroidPublisher publisher = new AndroidPublisher.Builder(transport,
                    JacksonFactory.getDefaultInstance(), credential).build();
            AndroidPublisher.Purchases.Subscriptions subscriptions = publisher.purchases().subscriptions();
            AndroidPublisher.Purchases.Subscriptions.Get subscription = subscriptions.get(googlePayDto.getPackageName(), googlePayDto.getProductId(), googlePayDto.getPurchaseToken());

            subscriptionPurchase = subscription.execute();
            log.info("结果{}", subscriptionPurchase);
        } catch (Exception e) {
            log.info("[请求谷歌api出错了]");
            e.printStackTrace();
        }
    }


    public void dealProductsData(GooglePayDto googlePayDto, ProductsInfo productsInfo) {
        String orderId = productsInfo.getOrderId();
        Integer purchaseState = productsInfo.getPurchaseState();
        Integer acknowledgementState = productsInfo.getAcknowledgementState();
        Integer consumptionState = productsInfo.getConsumptionState();
    }

}
