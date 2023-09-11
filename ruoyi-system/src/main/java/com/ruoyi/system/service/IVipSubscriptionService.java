package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.VipSubscription;

/**
 * 会员订阅信息Service接口
 * 
 * @author ruoyi
 * @date 2023-09-11
 */
public interface IVipSubscriptionService 
{
    /**
     * 查询会员订阅信息
     * 
     * @param id 会员订阅信息主键
     * @return 会员订阅信息
     */
    public VipSubscription selectVipSubscriptionById(Long id);

    /**
     * 查询会员订阅信息列表
     * 
     * @param vipSubscription 会员订阅信息
     * @return 会员订阅信息集合
     */
    public List<VipSubscription> selectVipSubscriptionList(VipSubscription vipSubscription);

    /**
     * 新增会员订阅信息
     * 
     * @param vipSubscription 会员订阅信息
     * @return 结果
     */
    public int insertVipSubscription(VipSubscription vipSubscription);

    /**
     * 修改会员订阅信息
     * 
     * @param vipSubscription 会员订阅信息
     * @return 结果
     */
    public int updateVipSubscription(VipSubscription vipSubscription);

    /**
     * 批量删除会员订阅信息
     * 
     * @param ids 需要删除的会员订阅信息主键集合
     * @return 结果
     */
    public int deleteVipSubscriptionByIds(Long[] ids);

    /**
     * 删除会员订阅信息信息
     * 
     * @param id 会员订阅信息主键
     * @return 结果
     */
    public int deleteVipSubscriptionById(Long id);
}
