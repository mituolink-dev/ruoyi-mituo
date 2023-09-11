package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.VipProducts;

/**
 * 会员内购信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-09-11
 */
public interface VipProductsMapper 
{
    /**
     * 查询会员内购信息
     * 
     * @param id 会员内购信息主键
     * @return 会员内购信息
     */
    public VipProducts selectVipProductsById(Long id);

    /**
     * 查询会员内购信息列表
     * 
     * @param vipProducts 会员内购信息
     * @return 会员内购信息集合
     */
    public List<VipProducts> selectVipProductsList(VipProducts vipProducts);

    /**
     * 新增会员内购信息
     * 
     * @param vipProducts 会员内购信息
     * @return 结果
     */
    public int insertVipProducts(VipProducts vipProducts);

    /**
     * 修改会员内购信息
     * 
     * @param vipProducts 会员内购信息
     * @return 结果
     */
    public int updateVipProducts(VipProducts vipProducts);

    /**
     * 删除会员内购信息
     * 
     * @param id 会员内购信息主键
     * @return 结果
     */
    public int deleteVipProductsById(Long id);

    /**
     * 批量删除会员内购信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVipProductsByIds(Long[] ids);
}
