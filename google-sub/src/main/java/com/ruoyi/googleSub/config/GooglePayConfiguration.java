package com.ruoyi.googleSub.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/23 19:51
 */
@Configuration
@Slf4j
public class GooglePayConfiguration {
    // 将google 提供的私钥放入项目 classpath 下（放在项目 resources 文件夹下即可）
//    @Value(value = "classpath:privateKey.json")
//    private Resource resource;
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource("classpath:static/pc-api-6238943613488692785-165-b9c98c3ecfeb.json");
    @Bean
    public AndroidPublisher androidPublisher() throws IOException {
        // 凭证
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream())
                .createScoped(Sets.newHashSet(AndroidPublisherScopes.ANDROIDPUBLISHER));
        AndroidPublisher androidPublisher;
        try {
            androidPublisher = new AndroidPublisher.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    new HttpCredentialsAdapter(credentials)).build();
            log.info("初始化谷歌服务成功");
        } catch (GeneralSecurityException | IOException ex) {
            log.error("初始化Google服务失败：{}", ex);
            throw new RuntimeException(ex);
        }
        return androidPublisher;
    }
}
