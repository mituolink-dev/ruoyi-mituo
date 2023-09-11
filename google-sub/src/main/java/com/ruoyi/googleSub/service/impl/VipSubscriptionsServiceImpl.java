package com.ruoyi.googleSub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.googleSub.domain.dto.GooglePayDto;
import com.ruoyi.googleSub.domain.entity.SubscriptionInfo;
import com.ruoyi.googleSub.service.VipSubscriptionsService;
import com.ruoyi.system.domain.VipSubscription;
import com.ruoyi.system.mapper.VipSubscriptionDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author openV
 * @description 针对表【vip_subscriptions(会员订阅信息表)】的数据库操作Service实现
 * @createDate 2023-08-29 15:30:38
 */
@Service
public class VipSubscriptionsServiceImpl extends ServiceImpl<VipSubscriptionDTOMapper, VipSubscription>
        implements VipSubscriptionsService {

    @Autowired
    VipSubscriptionDTOMapper vipSubscriptionsMapper;

    @Override
    public void dealSubscriptionsData(GooglePayDto googlePayDto, SubscriptionInfo subscriptionInfo) {

        String orderId = subscriptionInfo.getOrderId();
        QueryWrapper<VipSubscription> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);

        VipSubscription vipSubscriptions = vipSubscriptionsMapper.selectOne(queryWrapper);
        VipSubscription vipSubscriptions1 = transformData(subscriptionInfo);

        if (vipSubscriptions != null) {
            vipSubscriptionsMapper.update(vipSubscriptions1, queryWrapper);
        } else {
            vipSubscriptions1.setSubscriptionId(googlePayDto.getProductId());
            vipSubscriptions1.setSubscriptionToken(googlePayDto.getPurchaseToken());
            vipSubscriptions1.setPackageName(googlePayDto.getPackageName());
            vipSubscriptions1.setCreateTime(new Date());
            vipSubscriptions1.setUpdateTime(new Date());
            vipSubscriptions1.setOrderId(subscriptionInfo.getOrderId());
            vipSubscriptionsMapper.insert(vipSubscriptions1);
        }
    }

    public VipSubscription transformData(SubscriptionInfo subscriptionInfo) {
        String orderId = subscriptionInfo.getOrderId();
        Boolean autoRenewing = subscriptionInfo.getAutoRenewing();
        Integer cancelReason = subscriptionInfo.getCancelReason();
        Integer acknowledgementState = subscriptionInfo.getAcknowledgementState();
        String startTimeMillis = subscriptionInfo.getStartTimeMillis();
        String expiryTimeMillis = subscriptionInfo.getExpiryTimeMillis();
        String autoResumeTimeMillis = subscriptionInfo.getAutoResumeTimeMillis();
        String priceAmountMicros = subscriptionInfo.getPriceAmountMicros();
        Integer paymentState = subscriptionInfo.getPaymentState();
        String userCancellationTimeMillis = subscriptionInfo.getUserCancellationTimeMillis();
//        Integer purchaseType = subscriptionInfo.getPurchaseType();
        VipSubscription vipSubscriptions = new VipSubscription();
        vipSubscriptions.setOrderId(orderId);
        vipSubscriptions.setAutoRenewing(autoRenewing ? 0 : 1);
        vipSubscriptions.setCancelReason(cancelReason);
        vipSubscriptions.setStatus(acknowledgementState);
        vipSubscriptions.setStartTime(new Date(Long.parseLong(String.valueOf(startTimeMillis == null ? 0 : startTimeMillis))));
        vipSubscriptions.setExpireTime(new Date(Long.parseLong(String.valueOf(expiryTimeMillis == null ? 0 : expiryTimeMillis))));
        vipSubscriptions.setAutoResumeTime(new Date(Long.parseLong(String.valueOf(autoResumeTimeMillis == null ? 0 : autoResumeTimeMillis))));
        vipSubscriptions.setPrice(BigDecimal.valueOf(Long.parseLong(priceAmountMicros), 2));
        vipSubscriptions.setPaymentState(paymentState);
        vipSubscriptions.setUserCancellationTime(new Date(Long.parseLong(userCancellationTimeMillis == null ? "0" : userCancellationTimeMillis)));
        return vipSubscriptions;
    }
}




