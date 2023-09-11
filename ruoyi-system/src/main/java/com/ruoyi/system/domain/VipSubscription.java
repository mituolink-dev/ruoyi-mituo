package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员订阅信息对象 vip_subscription
 * 
 * @author ruoyi
 * @date 2023-09-11
 */
public class VipSubscription extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderId;

    /** 是否续订，0-不续订，1-续订 */
    @Excel(name = "是否续订，0-不续订，1-续订")
    private Integer autoRenewing;

    /** 0。用户取消了订阅 1. 由于结算问题 2，系统取消了订阅 2. 订阅被新的订阅 3 取代了。开发者已取消订阅 */
    @Excel(name = "0。用户取消了订阅 1. 由于结算问题 2，系统取消了订阅 2. 订阅被新的订阅 3 取代了。开发者已取消订阅")
    private Integer cancelReason;

    /** 订阅产品的确认状态。可能的值包括：0。待确认 1. 已确认 */
    @Excel(name = "订阅产品的确认状态。可能的值包括：0。待确认 1. 已确认")
    private Integer status;

    /** 授予订阅时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "授予订阅时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 订阅到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订阅到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireTime;

    /** 自动续订时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "自动续订时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date autoResumeTime;

    /** 订阅价格 */
    @Excel(name = "订阅价格")
    private BigDecimal price;

    /** 订阅的付款状态。可能的值包括：0。1. 待处理付款付款已收讫 2. 免费试用 3. 延迟升级/降级

已取消、已过期的订阅不会显示。 */
    @Excel(name = "订阅的付款状态。可能的值包括：0。1. 待处理付款付款已收讫 2. 免费试用 3. 延迟升级/降级 已取消、已过期的订阅不会显示。")
    private Integer paymentState;

    /** 用户取消订阅时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "用户取消订阅时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date userCancellationTime;

    /** 订阅查询id */
    @Excel(name = "订阅查询id")
    private String subscriptionId;

    /** 包名 */
    @Excel(name = "包名")
    private String packageName;

    /** 订阅查询token */
    @Excel(name = "订阅查询token")
    private String subscriptionToken;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setAutoRenewing(Integer autoRenewing) 
    {
        this.autoRenewing = autoRenewing;
    }

    public Integer getAutoRenewing() 
    {
        return autoRenewing;
    }
    public void setCancelReason(Integer cancelReason) 
    {
        this.cancelReason = cancelReason;
    }

    public Integer getCancelReason() 
    {
        return cancelReason;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setExpireTime(Date expireTime) 
    {
        this.expireTime = expireTime;
    }

    public Date getExpireTime() 
    {
        return expireTime;
    }
    public void setAutoResumeTime(Date autoResumeTime) 
    {
        this.autoResumeTime = autoResumeTime;
    }

    public Date getAutoResumeTime() 
    {
        return autoResumeTime;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setPaymentState(Integer paymentState) 
    {
        this.paymentState = paymentState;
    }

    public Integer getPaymentState() 
    {
        return paymentState;
    }
    public void setUserCancellationTime(Date userCancellationTime) 
    {
        this.userCancellationTime = userCancellationTime;
    }

    public Date getUserCancellationTime() 
    {
        return userCancellationTime;
    }
    public void setSubscriptionId(String subscriptionId) 
    {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionId() 
    {
        return subscriptionId;
    }
    public void setPackageName(String packageName) 
    {
        this.packageName = packageName;
    }

    public String getPackageName() 
    {
        return packageName;
    }
    public void setSubscriptionToken(String subscriptionToken) 
    {
        this.subscriptionToken = subscriptionToken;
    }

    public String getSubscriptionToken() 
    {
        return subscriptionToken;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("autoRenewing", getAutoRenewing())
            .append("cancelReason", getCancelReason())
            .append("status", getStatus())
            .append("startTime", getStartTime())
            .append("expireTime", getExpireTime())
            .append("autoResumeTime", getAutoResumeTime())
            .append("price", getPrice())
            .append("paymentState", getPaymentState())
            .append("userCancellationTime", getUserCancellationTime())
            .append("subscriptionId", getSubscriptionId())
            .append("packageName", getPackageName())
            .append("subscriptionToken", getSubscriptionToken())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
