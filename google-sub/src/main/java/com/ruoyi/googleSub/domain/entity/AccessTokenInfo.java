package com.ruoyi.googleSub.domain.entity;

import lombok.Data;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/23 10:09
 */

@Data
public class AccessTokenInfo {
    private String access_token;
    private String expires_in;
    private String scope;
    private String token_type;
}
