package com.ruoyi.googleSub.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/18 9:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GooglePayDto {
    String packageName;
    String productId;
    String purchaseToken;


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPurchaseToken() {
        return purchaseToken;
    }

    public void setPurchaseToken(String purchaseToken) {
        this.purchaseToken = purchaseToken;
    }
}
