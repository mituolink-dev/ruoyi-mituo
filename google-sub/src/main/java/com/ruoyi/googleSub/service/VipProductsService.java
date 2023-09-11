package com.ruoyi.googleSub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.googleSub.domain.dto.GooglePayDto;
import com.ruoyi.googleSub.domain.entity.ProductsInfo;
import com.ruoyi.system.domain.VipProducts;


/**
* @author openV
* @description 针对表【vip_products(会员内购信息表)】的数据库操作Service
* @createDate 2023-08-29 13:37:42
*/
public interface VipProductsService extends IService<VipProducts> {
    public void dealProductsData(GooglePayDto googlePayDto, ProductsInfo productsInfo);
}
