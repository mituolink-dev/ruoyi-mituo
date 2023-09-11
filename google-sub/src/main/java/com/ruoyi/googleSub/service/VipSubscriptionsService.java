package com.ruoyi.googleSub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.googleSub.domain.dto.GooglePayDto;
import com.ruoyi.googleSub.domain.entity.SubscriptionInfo;
import com.ruoyi.system.domain.VipSubscription;


/**
* @author openV
* @description 针对表【vip_subscriptions(会员订阅信息表)】的数据库操作Service
* @createDate 2023-08-29 15:30:38
*/
public interface VipSubscriptionsService extends IService<VipSubscription> {

     void dealSubscriptionsData(GooglePayDto googlePayDto, SubscriptionInfo subscriptionInfo);
}
