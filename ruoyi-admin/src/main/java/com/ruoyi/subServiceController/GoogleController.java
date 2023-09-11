package com.ruoyi.subServiceController;

//import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson2.JSON;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.google.api.services.androidpublisher.model.SubscriptionPurchase;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Sets;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.resp.BaseResponse;
import com.ruoyi.common.resp.ResultUtils;
import com.ruoyi.googleSub.domain.dto.GooglePayDto;
import com.ruoyi.googleSub.domain.entity.ProductsInfo;
import com.ruoyi.googleSub.domain.entity.SubscriptionInfo;
import com.ruoyi.googleSub.service.sub.BasicRequestService;
import com.ruoyi.googleSub.utils.HttpRequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/18 9:29
 */
@Api("谷歌信息获取")
@RestController
@Slf4j
public class GoogleController {


//    @PostMapping("/checkOrder")
//    public SubscriptionPurchase checkOrder2(@RequestBody GooglePayDto googlePayDto,
//                                       HttpServletRequest requestDto) throws IOException, GeneralSecurityException {
//        //使用服务帐户Json文件获取Google凭据
//        List<String> scopes = new ArrayList<>();
//        scopes.add(AndroidPublisherScopes.ANDROIDPUBLISHER);
//        ResourceLoader resourceLoader = new DefaultResourceLoader();
//        Resource resource = resourceLoader.getResource("classpath:static/metro-link-396609-e3f1d8e33fd9.json");
//
//        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream())
//                .createScoped(scopes);
//        AndroidPublisher androidPublisher;
//        final SubscriptionPurchase purchase;
//        try {
//            androidPublisher = new AndroidPublisher.Builder(
//                    GoogleNetHttpTransport.newTrustedTransport(),
//                    JacksonFactory.getDefaultInstance(),
//                    new HttpCredentialsAdapter(credentials)).build();
//
//            AndroidPublisher.Purchases purchases = androidPublisher.purchases();
//            AndroidPublisher.Purchases.Subscriptions.Get request = purchases.subscriptions().get(googlePayDto.getPackageName(), googlePayDto.getProductId(), googlePayDto.getPurchaseToken());
//            System.out.println("===============" + request + "================");
//             purchase = request.execute();
//            log.info("初始化成功");
//        } catch (GeneralSecurityException | IOException ex) {
//            log.error("初始化Google服务失败：{}", ex);
//            throw new RuntimeException(ex);
//        }
//        //处理业务
//        return purchase;
//    }

    @Autowired
    HttpRequestUtil httpRequestUtil;

    @Autowired
    RedisCache redisCache;

    @Autowired
    BasicRequestService basicRequestService;

    @ApiOperation("使用代理谷歌api的方式获取信息")
    @PostMapping("/check3")
    public ProductPurchase checkOrder3(@RequestBody GooglePayDto googlePayDto,
                                       HttpServletRequest requestDto) throws IOException, GeneralSecurityException {
        // 设置代理服务器地址和端口
        System.setProperty("http.proxyHost", "http://127.0.0.1");
        System.setProperty("http.proxyPort", "1082");
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:static/pc-api-6238943613488692785-165-b9c98c3ecfeb.json");
        // 凭证
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream())
                .createScoped(Sets.newHashSet(AndroidPublisherScopes.ANDROIDPUBLISHER));

        AndroidPublisher androidPublisher;
        try {
            androidPublisher = new AndroidPublisher.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    new HttpCredentialsAdapter(credentials))
                    .setApplicationName("Metro-Link")
                    .build();
            log.info("初始化Google服务ok：{}", androidPublisher);
        } catch (GeneralSecurityException | IOException ex) {
            log.error("初始化Google服务失败：{}", ex);
            throw new RuntimeException(ex);
        }

        String originInfo = null;
        SubscriptionPurchase execute = null;
        ProductPurchase productPurchase = null;
        boolean googleServerError = false;
        try {
            //校验订单 一次性商品
//            productPurchase = androidPublisher.purchases().subscriptions()
//                    .get(googlePayDto.getPackageName(), googlePayDto.getProductId(), googlePayDto.getPurchaseToken()).execute();
            productPurchase = androidPublisher.purchases().products().get(googlePayDto.getPackageName(), googlePayDto.getProductId(), googlePayDto.getPurchaseToken())

                    .execute();

            originInfo = JSON.toJSONString(productPurchase);
            log.info("调用 GoogleSDK 获取订单数据，参数：{}，返回结果：{}", JSON.toJSONString(googlePayDto), originInfo);
            System.out.println(originInfo);

        } catch (Exception ex) {
            log.error("调用GoogleSDK校验订单发生异常:{} , 忽略异常，因为Google本地RSA校验已经通过", ex);
//            originInfo = request.getSigntureContent();
//            googleServerError = true;
        }
        return productPurchase;
    }

    @ApiOperation("通过httpclient获取信息")
    @GetMapping("/getAccessKeyByProxy")
    public BaseResponse<String> getAccessTokenByProxy() {
        String refreshToken = "1//06e07P-MQPK4jCgYIARAAGAYSNwF-L9Irn-86xmKXqCapsNXqths2daWfXxBTtce37PSJrZqalEBb9sQ_GTuM8LYMxVio0drm-kM";
        String grantType = "refresh_token";
        String clientSecret = "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";
        String clientId = "58453024511-vb562muoeep4jrkv6t9g3gh5a3tvvrtg.apps.googleusercontent.com";

        String accessKey = httpRequestUtil.grantAccessKey(grantType, clientId, refreshToken, clientSecret);
        return ResultUtils.success(accessKey);
    }

    @ApiOperation("不适用代理获取信息")
    @GetMapping("/getAccessTokenByLocal")
    public BaseResponse<String> getAccessTokenByLocal() {

        String refreshToken = "1//06D5F79K-ii8ACgYIARAAGAYSNwF-L9IrLZJw8gh3ktIilwO-dw6mVsVb2gn_gxwcJZcBND1gyRkgSJi3CGohBEfIWwfnLuTeqiw";
        String grantType = "refresh_token";
        String clientSecret = "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";
        String clientId = "58453024511-lfcp25mhco8fti4ltr4243e1h1c0kim5.apps.googleusercontent.com";

        String accessKey = httpRequestUtil.grantAccessKeyAsLocal(grantType, clientId, refreshToken, clientSecret);
        return ResultUtils.success(accessKey);
    }

    @ApiOperation("查询订阅信息")
    @PostMapping("/checkSubscriptionInfo")
    public BaseResponse<SubscriptionInfo> checkSubscriptionInfo(@RequestBody GooglePayDto googlePayDto,
                                                                HttpServletRequest requestDto) throws IOException, GeneralSecurityException {
        SubscriptionInfo subscriptionInfo = basicRequestService.getSubscriptionInfo(googlePayDto);
        if (subscriptionInfo.getOrderId() == null) {
            return ResultUtils.error(400, subscriptionInfo.getDescription());
        }
        return ResultUtils.success(subscriptionInfo, subscriptionInfo.getDescription());
    }


    @ApiOperation("查询内购详细")
    @PostMapping("/checkProductOrder")
    public BaseResponse<ProductsInfo> checkProductOrder(@RequestBody GooglePayDto googlePayDto,
                                                        HttpServletRequest requestDto) throws IOException, GeneralSecurityException {
        return ResultUtils.success(basicRequestService.getProductsInfo(googlePayDto));
    }

    @ApiOperation("查询内购产品状态")
    @PostMapping("/checkProductsStatus")
    public BaseResponse<String> checkProductInfo(@RequestBody GooglePayDto googlePayDto) {

        Map<String, String> res = basicRequestService.productsGoodsCheck(googlePayDto);

        return ResultUtils.success(res.get("status"), res.get("orderId"));
    }

    @ApiOperation("查询订阅产品状态")
    @PostMapping("/checkSubscriptionStatus")
    public BaseResponse<String> checkSubscriptionStatus(@RequestBody GooglePayDto googlePayDto) {

        Map<String, String> res = basicRequestService.subscriptionsGoodsCheck(googlePayDto);

        return ResultUtils.success(res.get("status"), res.get("orderId"));
    }

}
