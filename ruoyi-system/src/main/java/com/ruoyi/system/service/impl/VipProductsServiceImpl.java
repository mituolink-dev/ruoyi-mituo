package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.VipProductsMapper;
import com.ruoyi.system.domain.VipProducts;
import com.ruoyi.system.service.IVipProductsService;

/**
 * 会员内购信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-11
 */
@Service
public class VipProductsServiceImpl implements IVipProductsService {
    @Autowired
    private VipProductsMapper vipProductsMapper;

    /**
     * 查询会员内购信息
     *
     * @param id 会员内购信息主键
     * @return 会员内购信息
     */
    @Override
    public VipProducts selectVipProductsById(Long id) {
        return vipProductsMapper.selectVipProductsById(id);
    }

    /**
     * 查询会员内购信息列表
     *
     * @param vipProducts 会员内购信息
     * @return 会员内购信息
     */
    @Override
    public List<VipProducts> selectVipProductsList(VipProducts vipProducts) {
        return vipProductsMapper.selectVipProductsList(vipProducts);
    }

    /**
     * 新增会员内购信息
     *
     * @param vipProducts 会员内购信息
     * @return 结果
     */
    @Override
    public int insertVipProducts(VipProducts vipProducts) {
        vipProducts.setCreateTime(DateUtils.getNowDate());
        return vipProductsMapper.insertVipProducts(vipProducts);
    }

    /**
     * 修改会员内购信息
     *
     * @param vipProducts 会员内购信息
     * @return 结果
     */
    @Override
    public int updateVipProducts(VipProducts vipProducts) {
        vipProducts.setUpdateTime(DateUtils.getNowDate());
        return vipProductsMapper.updateVipProducts(vipProducts);
    }

    /**
     * 批量删除会员内购信息
     *
     * @param ids 需要删除的会员内购信息主键
     * @return 结果
     */
    @Override
    public int deleteVipProductsByIds(Long[] ids) {
        return vipProductsMapper.deleteVipProductsByIds(ids);
    }

    /**
     * 删除会员内购信息信息
     *
     * @param id 会员内购信息主键
     * @return 结果
     */
    @Override
    public int deleteVipProductsById(Long id) {
        return vipProductsMapper.deleteVipProductsById(id);
    }
}
