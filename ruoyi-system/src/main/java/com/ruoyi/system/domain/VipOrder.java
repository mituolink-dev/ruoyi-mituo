package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员订单对象 vip_order
 * 
 * @author ruoyi
 * @date 2023-08-24
 */
public class VipOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderSn;

    /** 第三方订单编号 */
    @Excel(name = "第三方订单编号")
    private String thirdOrderSn;

    /** 源订单号，发起退款时关联的订单 */
    @Excel(name = "源订单号，发起退款时关联的订单")
    private String originOrderSn;

    /** 订单类型 0 一次性购买 1 周期扣款订单 */
    @Excel(name = "订单类型 0 一次性购买 1 周期扣款订单")
    private Integer type;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private Long price;

    /** 分销渠道 */
    @Excel(name = "分销渠道")
    private String distributionChannel;

    /** 付款方式：0 Google pay  */
    @Excel(name = "付款方式：0 Google pay ")
    private String paymentMethod;

    /** 订单状态：0：初始化 1：已完成 2 已经取消 3 已退款 */
    @Excel(name = "订单状态：0：初始化 1：已完成 2 已经取消 3 已退款")
    private Integer status;

    /** 发货状态：0：发货失败 1：发货成功 */
    @Excel(name = "发货状态：0：发货失败 1：发货成功")
    private Integer deliverStatus;

    /** 订单完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completeTime;

    /** 退款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date refundTime;

    /** 付款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 设备ID */
    @Excel(name = "设备ID")
    private String deviceId;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 第三方支付信息 */
    @Excel(name = "第三方支付信息")
    private String originInfo;

    /** 是否支持退款 */
    @Excel(name = "是否支持退款")
    private Integer supportRefund;

    /** 退款原因 */
    @Excel(name = "退款原因")
    private String refundReason;

    /** 商品信息 */
    @Excel(name = "商品信息")
    private String goodsInfo;

    /** 包名 */
    @Excel(name = "包名")
    private String packageName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderSn(String orderSn) 
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn() 
    {
        return orderSn;
    }
    public void setThirdOrderSn(String thirdOrderSn) 
    {
        this.thirdOrderSn = thirdOrderSn;
    }

    public String getThirdOrderSn() 
    {
        return thirdOrderSn;
    }
    public void setOriginOrderSn(String originOrderSn) 
    {
        this.originOrderSn = originOrderSn;
    }

    public String getOriginOrderSn() 
    {
        return originOrderSn;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setDistributionChannel(String distributionChannel) 
    {
        this.distributionChannel = distributionChannel;
    }

    public String getDistributionChannel() 
    {
        return distributionChannel;
    }
    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() 
    {
        return paymentMethod;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setDeliverStatus(Integer deliverStatus) 
    {
        this.deliverStatus = deliverStatus;
    }

    public Integer getDeliverStatus() 
    {
        return deliverStatus;
    }
    public void setCompleteTime(Date completeTime) 
    {
        this.completeTime = completeTime;
    }

    public Date getCompleteTime() 
    {
        return completeTime;
    }
    public void setRefundTime(Date refundTime) 
    {
        this.refundTime = refundTime;
    }

    public Date getRefundTime() 
    {
        return refundTime;
    }
    public void setPayTime(Date payTime) 
    {
        this.payTime = payTime;
    }

    public Date getPayTime() 
    {
        return payTime;
    }
    public void setDeviceId(String deviceId) 
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId() 
    {
        return deviceId;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setOriginInfo(String originInfo) 
    {
        this.originInfo = originInfo;
    }

    public String getOriginInfo() 
    {
        return originInfo;
    }
    public void setSupportRefund(Integer supportRefund) 
    {
        this.supportRefund = supportRefund;
    }

    public Integer getSupportRefund() 
    {
        return supportRefund;
    }
    public void setRefundReason(String refundReason) 
    {
        this.refundReason = refundReason;
    }

    public String getRefundReason() 
    {
        return refundReason;
    }
    public void setGoodsInfo(String goodsInfo) 
    {
        this.goodsInfo = goodsInfo;
    }

    public String getGoodsInfo() 
    {
        return goodsInfo;
    }
    public void setPackageName(String packageName) 
    {
        this.packageName = packageName;
    }

    public String getPackageName() 
    {
        return packageName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderSn", getOrderSn())
            .append("thirdOrderSn", getThirdOrderSn())
            .append("originOrderSn", getOriginOrderSn())
            .append("type", getType())
            .append("userId", getUserId())
            .append("goodsId", getGoodsId())
            .append("price", getPrice())
            .append("distributionChannel", getDistributionChannel())
            .append("paymentMethod", getPaymentMethod())
            .append("status", getStatus())
            .append("deliverStatus", getDeliverStatus())
            .append("completeTime", getCompleteTime())
            .append("refundTime", getRefundTime())
            .append("payTime", getPayTime())
            .append("deviceId", getDeviceId())
            .append("ip", getIp())
            .append("country", getCountry())
            .append("originInfo", getOriginInfo())
            .append("remark", getRemark())
            .append("supportRefund", getSupportRefund())
            .append("refundReason", getRefundReason())
            .append("goodsInfo", getGoodsInfo())
            .append("packageName", getPackageName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
