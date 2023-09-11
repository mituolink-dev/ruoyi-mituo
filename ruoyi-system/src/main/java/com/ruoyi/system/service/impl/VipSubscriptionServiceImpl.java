package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.VipSubscriptionMapper;
import com.ruoyi.system.domain.VipSubscription;
import com.ruoyi.system.service.IVipSubscriptionService;

/**
 * 会员订阅信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-11
 */
@Service
public class VipSubscriptionServiceImpl implements IVipSubscriptionService 
{
    @Autowired
    private VipSubscriptionMapper vipSubscriptionMapper;

    /**
     * 查询会员订阅信息
     * 
     * @param id 会员订阅信息主键
     * @return 会员订阅信息
     */
    @Override
    public VipSubscription selectVipSubscriptionById(Long id)
    {
        return vipSubscriptionMapper.selectVipSubscriptionById(id);
    }

    /**
     * 查询会员订阅信息列表
     * 
     * @param vipSubscription 会员订阅信息
     * @return 会员订阅信息
     */
    @Override
    public List<VipSubscription> selectVipSubscriptionList(VipSubscription vipSubscription)
    {
        return vipSubscriptionMapper.selectVipSubscriptionList(vipSubscription);
    }

    /**
     * 新增会员订阅信息
     * 
     * @param vipSubscription 会员订阅信息
     * @return 结果
     */
    @Override
    public int insertVipSubscription(VipSubscription vipSubscription)
    {
        vipSubscription.setCreateTime(DateUtils.getNowDate());
        return vipSubscriptionMapper.insertVipSubscription(vipSubscription);
    }

    /**
     * 修改会员订阅信息
     * 
     * @param vipSubscription 会员订阅信息
     * @return 结果
     */
    @Override
    public int updateVipSubscription(VipSubscription vipSubscription)
    {
        vipSubscription.setUpdateTime(DateUtils.getNowDate());
        return vipSubscriptionMapper.updateVipSubscription(vipSubscription);
    }

    /**
     * 批量删除会员订阅信息
     * 
     * @param ids 需要删除的会员订阅信息主键
     * @return 结果
     */
    @Override
    public int deleteVipSubscriptionByIds(Long[] ids)
    {
        return vipSubscriptionMapper.deleteVipSubscriptionByIds(ids);
    }

    /**
     * 删除会员订阅信息信息
     * 
     * @param id 会员订阅信息主键
     * @return 结果
     */
    @Override
    public int deleteVipSubscriptionById(Long id)
    {
        return vipSubscriptionMapper.deleteVipSubscriptionById(id);
    }
}
