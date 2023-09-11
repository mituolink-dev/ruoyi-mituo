package com.ruoyi.googleSub.domain.entity;

import lombok.Data;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/23 19:35
 */
@Data
public class GooglePayVerifyRequest {

    private String packageName;

    private String productId;

    private String purchaseToken;
}
