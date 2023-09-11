package com.ruoyi.googleSub.domain.entity;

import lombok.Data;

/**
 * 订阅信息
 *
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/23 17:21
 */
@Data
public class SubscriptionInfo {
    /**
     * 此类型表示 androidpublisherservice 中的 subscriptionPurchase 对象。
     */
    private String kind;

    /**
     * 授予订阅的时间，以从公元纪年开始计算的毫秒数表示。
     */
    private String startTimeMillis;


    /**
     * 订阅的到期时间（以毫秒为单位，从 Epoch 起算）。
     */
    private String expiryTimeMillis;

    /**
     * 自动恢复订阅的时间（以毫秒为单位，从 Epoch 起算）。仅当用户请求暂停订阅时才存在。
     */
    private String autoResumeTimeMillis;

    /**
     * 设置是否在订阅到期时自动续订。
     */
    private Boolean autoRenewing;

    /**
     * 订阅价格的 ISO 4217 货币代码。例如，如果价格采用英镑，则 priceCurrencyCode 为“GBP”。
     */
    private String priceCurrencyCode;

    /**
     * 订阅价格。对于不含税的国家/地区，价格不包含税费。对于含税的国家/地区，价格中包含税费。
     * 价格以微单位显示，其中 1000000 个微单位表示货币单位。例如，如果订阅价格为 1.99 欧元，
     * priceAmountMicros 为 1990000。
     */
    private String priceAmountMicros;

    /**
     * 订阅获得批准的用户的 ISO 3166-1 alpha-2 帐单邮寄地址所在国家/地区代码
     */
    private String countryCode;

    /**
     * 开发者指定的字符串，其中包含有关订单的补充信息。
     */
    private String developerPayload;

    /**
     * 订阅的付款状态。可能的值包括：0。1. 待处理付款付款已收讫 2. 免费试用 3. 延迟升级/降级
     */
    private Integer paymentState;

    /**
     * 订阅被取消或未自动续订的原因。
     * 可能的值包括：
     * 0。用户取消了订阅
     * 1. 由于结算问题 2，系统取消了订阅
     * 2. 订阅被新的订阅 3 取代了。开发者已取消订阅
     */
    private Integer cancelReason;

    /**
     * 用户取消订阅的时间，以从公元纪年开始计算的毫秒数表示。仅当 CancelReason 为 0 时才存在。
     */
    private String userCancellationTimeMillis;

    /**
     * 与购买订阅有关的最新周期性订单的订单 ID。
     * 如果订阅因付款遭拒而被取消，系统将会显示付款遭拒订单的订单 ID。
     */
    private String orderId;

    /**
     * 源购买交易的购买令牌（如果此订阅是以下之一）：
     * 0。重新注册已取消但尚未失效的订阅
     * 1. 从之前的订阅升级/降级
     *
     * 例如，假设某个用户先注册后收到购买令牌 X，
     * 然后该用户取消并完成了重新注册流程（在订阅到期之前），
     * 您收到了购买令牌 Y，最后该用户升级了订阅，这时您收到了购买令牌 Z。
     * 如果您使用购买令牌 Z 调用此 API，此字段将设置为 Y。
     * 如果您使用购买令牌 Y 调用此 API，此字段将设置为 X。如果您使用购买令牌 X 调用此 API，将不会设置此字段。
     */
    private String linkedPurchaseToken;

    /**
     * 订阅的购买类型。
     * 仅当购买交易不是使用标准的应用内购买结算流程时，系统才会设置此字段。
     * 可能的值包括：
     * 0。测试（即通过许可测试帐号购买）
     * 1.促销（即使用促销代码购买）
     */
    private Integer purchaseType;


    /**
     * 购买订阅的用户的个人资料名称。只有通过“在 Google 上购买”的购买交易才能使用此功能。
     */
    private String profileName;

    /**
     * 购买订阅时用户的电子邮件地址。只有通过“在 Google 上购买”的购买交易才能使用此功能。
     */
    private String emailAddress;

    /**
     * 购买订阅时用户的指定名称。只有通过“在 Google 上购买”的购买交易才能使用此功能。
     */
    private String givenName;

    /**
     * 订阅产品的确认状态。可能的值包括：
     * 0。待确认
     * 1. 已确认
     */
    private Integer acknowledgementState;


    /**
     * 与用户的用户帐号唯一关联的 ID 的混淆版本。
     */
    private String obfuscatedExternalAccountId;


    /**
     * 自定义的字段，作为请求结果的描述
     */
    private String description;


}
