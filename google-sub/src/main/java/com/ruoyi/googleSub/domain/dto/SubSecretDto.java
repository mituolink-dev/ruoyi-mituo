package com.ruoyi.googleSub.domain.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/18 10:38
 */
@Data
@Component
@ConfigurationProperties(prefix = "secret")
public class SubSecretDto {

    private String grantType;

    private String ClientId;

    private String refreshToken;

    private String clientSecret;
}
