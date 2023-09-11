package com.ruoyi.googleSub.domain.entity;

import lombok.Data;

/**
 * 内购信息
 *
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/23 17:32
 */

@Data
public class ProductsInfo {

    /**
     * 这种类型表示 androidpublisherservice 中的 inappPurchase 对象。
     */
    private String kind;

    /**
     * 购买商品的时间，以从公元纪年（1970 年 1 月 1 日）开始计算的毫秒数表示。
     */
    private String purchaseTimeMillis;

    /**
     * 订单的购买状态。可能的值包括：0。购买 1. 已取消 2. 待处理
     */
    private Integer purchaseState;

    /**
     * 应用内商品的消耗状态。可能的值包括：0。尚未消耗 1. 已使用
     */
    private Integer consumptionState;

    /**
     * 开发者指定的字符串，其中包含关于订单的补充信息。
     */
    private String developerPayload;

    /**
     * 与购买应用内商品相关的订单 ID。
     */
    private String orderId;

    /**
     * 应用内商品的购买类型。
     * 仅当购买交易不是使用标准的应用内购买结算流程完成时，系统才会设置此字段。
     * 可能的值包括：
     * 0。测试（即从许可测试帐号中购买的服务）
     * 1.促销（即使用促销代码购买）
     * 2.激励广告（即通过观看视频广告而不是付费）
     */
    private String purchaseType;

    /**
     * 应用内商品的确认状态。可能的值包括：0。尚未确认 1. 已确认
     */
    private Integer acknowledgementState;


    private String obfuscatedExternalAccountId;

    /**
     * 产品被授予时用户的 ISO 3166-1 alpha-2 结算区域代码。
     */
    private String regionCode;

}
