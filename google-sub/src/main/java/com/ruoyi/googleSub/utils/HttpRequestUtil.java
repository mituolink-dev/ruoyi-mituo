package com.ruoyi.googleSub.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.google.api.services.androidpublisher.model.SubscriptionPurchase;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.googleSub.constant.HttpClientConstant;
import com.ruoyi.googleSub.domain.dto.GooglePayDto;
import com.ruoyi.googleSub.domain.entity.AccessTokenInfo;
import com.ruoyi.googleSub.domain.entity.ProductsInfo;
import com.ruoyi.googleSub.domain.entity.SubscriptionInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * HttpRequest工具类
 *
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/23 9:06
 */

@Component
@Slf4j
public class HttpRequestUtil {


    @Autowired
    AndroidPublisher androidPublisher;

    @Autowired
    RedisCache redisCache;

    CloseableHttpClient httpClient = HttpClients.createDefault();

    public synchronized String grantAccessKey(String grantType, String clientId, String refreshToken, String clientSecret) {
        if (redisCache.getCacheObject(HttpClientConstant.ACCESSTOKEN) != null) {
            return redisCache.getCacheObject(HttpClientConstant.ACCESSTOKEN);
        }

        UrlEncodedFormEntity urlEncodedFormEntity;
        HttpHost target = new HttpHost("accounts.google.com", 443, "https");  //这个是目标主机，即您要访问的服务器，比较网站。
        HttpHost proxy = new HttpHost("127.0.0.1", 1082, "http");  //这个是代理信息，即我们要通过哪个代理主机进行代理请求访问。

        RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .build();
        HttpPost httpPost = new HttpPost("/o/oauth2/token");
        httpPost.setConfig(config);

        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair(HttpClientConstant.REFRESHTOKEN, refreshToken));
        formparams.add(new BasicNameValuePair(HttpClientConstant.GRANTTYPE, grantType));
        formparams.add(new BasicNameValuePair(HttpClientConstant.CLIENTSECRET, clientSecret));
        formparams.add(new BasicNameValuePair(HttpClientConstant.CLIENTID, clientId));
        System.out.println();
        log.info("Executing request {} to {} via {} ", httpPost.getRequestLine(), target, proxy);
        String accessToken = null;
        try {
            urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);
            try {
                CloseableHttpResponse response = httpClient.execute(target, httpPost);  //执行请求。

                log.info("状态码：{}", response.getStatusLine());
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                accessToken = JSONObject.parseObject(result, AccessTokenInfo.class).getAccess_token();

                accessToken = accessToken == null ? result : accessToken;
                if (!accessToken.equals(result)) {
                    redisCache.setCacheObject(HttpClientConstant.ACCESSTOKEN, accessToken, 3500, TimeUnit.SECONDS);
                }
                log.info("Response content: {}", result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return accessToken;
    }


    public String grantAccessKeyAsLocal(String grantType, String clientId, String refreshToken, String clientSecret) {
        UrlEncodedFormEntity urlEncodedFormEntity;
        // 设置代理服务器地址和端口
        System.setProperty("http.proxyHost", "http://127.0.0.1");
        System.setProperty("http.proxyPort", "1082");
        HttpHost target = new HttpHost("accounts.google.com", 443, "https");  //这个是目标主机，即您要访问的服务器，比较网站。

        HttpPost httpPost = new HttpPost("/o/oauth2/token");
//        httpPost.setConfig(config);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<>();

        formparams.add(new BasicNameValuePair("refresh_token", refreshToken));
        formparams.add(new BasicNameValuePair("grant_type", grantType));
        formparams.add(new BasicNameValuePair("client_secret", clientSecret));
        formparams.add(new BasicNameValuePair("client_id", clientId));
        log.info("Executing request {} to {} ", httpPost.getRequestLine(), target);
        String accessToken = null;


        try {
            urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);
            try {
                CloseableHttpResponse response = httpClient.execute(target, httpPost);  //执行请求。
                log.info("状态码：{}", response.getStatusLine());
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                accessToken = JSONObject.parseObject(result, AccessTokenInfo.class).getAccess_token();

                log.info("Response content: {}", result);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return accessToken;
    }


    public void testSDK() throws IOException {

        GooglePayDto googlePayDto = new GooglePayDto("com.metrolinkstudio.metrolink", "002", "ofoahmcnknnaohfhgaabenae.AO-J1OzkSmNFIi42-hbTwk1leiYw59iOAxsER2ANUsqZbc07qgMPIbp89wowEkjXBCYkDgMdIarkhlKCLScIu8HY3no7pKXvKFyFfUpto7bXZrOAG1ohWBM");

        String originInfo = null;
        SubscriptionPurchase execute = null;
        ProductPurchase productPurchase = null;
        try {
            execute = androidPublisher.purchases().subscriptions().get(googlePayDto.getPackageName(), googlePayDto.getProductId(), googlePayDto.getPurchaseToken())
                    .execute();
            originInfo = JSON.toJSONString(execute);
            log.info("调用 GoogleSDK 获取订单数据，参数：{}，返回结果：{}", JSON.toJSONString(googlePayDto), originInfo);
            System.out.println(originInfo);

        } catch (Exception ex) {
            log.error("调用GoogleSDK校验订单发生异常:{} , 忽略异常，因为Google本地RSA校验已经通过", ex);
        }
    }

    public SubscriptionInfo getSubscriptionInfo(String accessToken, GooglePayDto googlePayDto) throws URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder("https://androidpublisher.googleapis.com/androidpublisher/v3/applications/" + googlePayDto.getPackageName() + "/purchases/subscriptions/" + googlePayDto.getProductId() + "/tokens/" + googlePayDto.getPurchaseToken());

        HttpHost proxy = new HttpHost("127.0.0.1", 1082, "http");  //这个是代理信息，即我们要通过哪个代理主机进行代理请求访问。
        RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .build();

        // 创建参数队列
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair(HttpClientConstant.ACCESSTOKEN, accessToken);
        list.add(param1);
        uriBuilder.setParameters(list);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setConfig(config);
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        log.info("Executing request {} to {} via {} ", httpGet.getRequestLine(), uriBuilder, proxy);

        SubscriptionInfo subscriptionInfo = new SubscriptionInfo();

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);  //执行请求。

            log.info("状态码：{}", response.getStatusLine());

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                String result = EntityUtils.toString(entity, "UTF-8");
                subscriptionInfo = JSONObject.parseObject(result, SubscriptionInfo.class);

                subscriptionInfo.setDescription("OK");

                log.info("Response content: {}", result);

                log.info("解析成功subscriptionInfo：{}", subscriptionInfo);
            } else {
                subscriptionInfo.setDescription(response.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return subscriptionInfo;
    }


    public ProductsInfo getProductsInfo(String accessToken, GooglePayDto googlePayDto) throws URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder("https://androidpublisher.googleapis.com/androidpublisher/v3/applications/" + googlePayDto.getPackageName() + "/purchases/products/" + googlePayDto.getProductId() + "/tokens/" + googlePayDto.getPurchaseToken());

        HttpHost proxy = new HttpHost("127.0.0.1", 1082, "http");  //这个是代理信息，即我们要通过哪个代理主机进行代理请求访问。
        RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .build();

        // 创建参数队列
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair(HttpClientConstant.ACCESSTOKEN, accessToken);
        list.add(param1);
        uriBuilder.setParameters(list);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setConfig(config);
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        log.info("Executing request {} to {} via {} ", httpGet.getRequestLine(), uriBuilder, proxy);

        ProductsInfo productsInfo = new ProductsInfo();

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);  //执行请求。

            log.info("状态码：{}", response.getStatusLine());
            HttpEntity entity = response.getEntity();
//            InputStream content = entity.getContent();
            String result = EntityUtils.toString(entity, "UTF-8");
            productsInfo = JSONObject.parseObject(result, ProductsInfo.class);

            log.info("Response content: {}", result);

            log.info("解析成功subscriptionInfo：{}", productsInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return productsInfo;
    }

}
