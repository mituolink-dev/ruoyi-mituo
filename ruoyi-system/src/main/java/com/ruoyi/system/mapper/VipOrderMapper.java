package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.VipOrder;

/**
 * 会员订单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-24
 */
public interface VipOrderMapper 
{
    /**
     * 查询会员订单
     * 
     * @param id 会员订单主键
     * @return 会员订单
     */
    public VipOrder selectVipOrderById(Long id);

    /**
     * 查询会员订单列表
     * 
     * @param vipOrder 会员订单
     * @return 会员订单集合
     */
    public List<VipOrder> selectVipOrderList(VipOrder vipOrder);

    /**
     * 新增会员订单
     * 
     * @param vipOrder 会员订单
     * @return 结果
     */
    public int insertVipOrder(VipOrder vipOrder);

    /**
     * 修改会员订单
     * 
     * @param vipOrder 会员订单
     * @return 结果
     */
    public int updateVipOrder(VipOrder vipOrder);

    /**
     * 删除会员订单
     * 
     * @param id 会员订单主键
     * @return 结果
     */
    public int deleteVipOrderById(Long id);

    /**
     * 批量删除会员订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVipOrderByIds(Long[] ids);
}
