package com.ruoyi.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员内购信息对象 vip_products
 *
 * @author ruoyi
 * @date 2023-09-11
 */
public class VipProducts extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 订单id
     */
    @Excel(name = "订单id")
    private String orderId;

    /**
     * 购买类型
     */
    @Excel(name = "购买类型")
    private String purchaseType;

    /**
     * 购买时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseTime;

    /**
     * 订单状态0。尚未确认 1. 已确认
     */
    @Excel(name = "订单状态0。尚未确认 1. 已确认")
    private Integer status;

    /**
     * 订单的购买状态。可能的值包括：0。购买 1. 已取消 2. 待处理
     */
    @Excel(name = "订单的购买状态。可能的值包括：0。购买 1. 已取消 2. 待处理")
    private Integer purchaseState;

    /**
     * 应用内商品的消耗状态。可能的值包括：0。尚未消耗 1. 已使用
     */
    @Excel(name = "应用内商品的消耗状态。可能的值包括：0。尚未消耗 1. 已使用")
    private Integer consumptionState;

    /**
     * 产品id
     */
    @Excel(name = "产品id")
    private String productId;

    /**
     * 产品token
     */
    @Excel(name = "产品token")
    private String productToken;

    /**
     * 包名
     */
    @Excel(name = "包名")
    private String packageName;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setPurchaseState(Integer purchaseState) {
        this.purchaseState = purchaseState;
    }

    public Integer getPurchaseState() {
        return purchaseState;
    }

    public void setConsumptionState(Integer consumptionState) {
        this.consumptionState = consumptionState;
    }

    public Integer getConsumptionState() {
        return consumptionState;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductToken(String productToken) {
        this.productToken = productToken;
    }

    public String getProductToken() {
        return productToken;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("purchaseType", getPurchaseType())
                .append("purchaseTime", getPurchaseTime())
                .append("status", getStatus())
                .append("purchaseState", getPurchaseState())
                .append("consumptionState", getConsumptionState())
                .append("productId", getProductId())
                .append("productToken", getProductToken())
                .append("packageName", getPackageName())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
