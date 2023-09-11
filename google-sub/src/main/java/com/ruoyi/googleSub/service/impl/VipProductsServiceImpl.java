package com.ruoyi.googleSub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.googleSub.domain.dto.GooglePayDto;
import com.ruoyi.googleSub.domain.entity.ProductsInfo;
import com.ruoyi.googleSub.service.VipProductsService;
import com.ruoyi.system.domain.VipProducts;
import com.ruoyi.system.mapper.VipProductsDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author openV
 * @description 针对表【vip_products(会员内购信息表)】的数据库操作Service实现
 * @createDate 2023-08-29 13:37:42
 */
@Service
public class VipProductsServiceImpl extends ServiceImpl<VipProductsDTOMapper, VipProducts>
        implements VipProductsService {

    @Autowired
    VipProductsDTOMapper vipProductsDTOMapper;

    @Override
    public void dealProductsData(GooglePayDto googlePayDto, ProductsInfo productsInfo) {
        String orderId = productsInfo.getOrderId();


        QueryWrapper<VipProducts> queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_id", orderId);

        VipProducts vipProducts = vipProductsDTOMapper.selectOne(queryWrapper);

        VipProducts vipProducts1 = transformData(productsInfo);

        if (vipProducts != null) {
            vipProducts1.setUpdateTime(new Date());
            vipProductsDTOMapper.update(vipProducts1, queryWrapper);

        } else {
            vipProducts1.setOrderId(productsInfo.getOrderId());
            vipProducts1.setCreateTime(new Date());
            vipProducts1.setUpdateTime(new Date());
            vipProducts1.setProductId( googlePayDto.getProductId());
            vipProducts1.setProductToken(googlePayDto.getPurchaseToken());
            vipProducts1.setPackageName(googlePayDto.getPackageName());
            vipProductsDTOMapper.insert(vipProducts1);
        }
    }


    public VipProducts transformData(ProductsInfo productsInfo) {

        Integer purchaseState = productsInfo.getPurchaseState();
        Integer acknowledgementState = productsInfo.getAcknowledgementState();
        String purchaseType = productsInfo.getPurchaseType();
        Integer consumptionState = productsInfo.getConsumptionState();
        String purchaseTimeMillis = productsInfo.getPurchaseTimeMillis();

        VipProducts vipProducts = new VipProducts();

        vipProducts.setConsumptionState(consumptionState);
        vipProducts.setPurchaseState(purchaseState);

        vipProducts.setPurchaseTime(new Date(Long.parseLong(String.valueOf(purchaseTimeMillis))));
        vipProducts.setPurchaseType(purchaseType);
        vipProducts.setStatus(acknowledgementState);
        return vipProducts;
    }
}




