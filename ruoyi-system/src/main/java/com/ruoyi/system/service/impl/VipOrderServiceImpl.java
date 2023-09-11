package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.VipOrderMapper;
import com.ruoyi.system.domain.VipOrder;
import com.ruoyi.system.service.IVipOrderService;

/**
 * 会员订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-24
 */
@Service
public class VipOrderServiceImpl implements IVipOrderService 
{
    @Autowired
    private VipOrderMapper vipOrderMapper;

    /**
     * 查询会员订单
     * 
     * @param id 会员订单主键
     * @return 会员订单
     */
    @Override
    public VipOrder selectVipOrderById(Long id)
    {
        return vipOrderMapper.selectVipOrderById(id);
    }

    /**
     * 查询会员订单列表
     * 
     * @param vipOrder 会员订单
     * @return 会员订单
     */
    @Override
    public List<VipOrder> selectVipOrderList(VipOrder vipOrder)
    {
        return vipOrderMapper.selectVipOrderList(vipOrder);
    }

    /**
     * 新增会员订单
     * 
     * @param vipOrder 会员订单
     * @return 结果
     */
    @Override
    public int insertVipOrder(VipOrder vipOrder)
    {
        vipOrder.setCreateTime(DateUtils.getNowDate());
        return vipOrderMapper.insertVipOrder(vipOrder);
    }

    /**
     * 修改会员订单
     * 
     * @param vipOrder 会员订单
     * @return 结果
     */
    @Override
    public int updateVipOrder(VipOrder vipOrder)
    {
        vipOrder.setUpdateTime(DateUtils.getNowDate());
        return vipOrderMapper.updateVipOrder(vipOrder);
    }

    /**
     * 批量删除会员订单
     * 
     * @param ids 需要删除的会员订单主键
     * @return 结果
     */
    @Override
    public int deleteVipOrderByIds(Long[] ids)
    {
        return vipOrderMapper.deleteVipOrderByIds(ids);
    }

    /**
     * 删除会员订单信息
     * 
     * @param id 会员订单主键
     * @return 结果
     */
    @Override
    public int deleteVipOrderById(Long id)
    {
        return vipOrderMapper.deleteVipOrderById(id);
    }
}
