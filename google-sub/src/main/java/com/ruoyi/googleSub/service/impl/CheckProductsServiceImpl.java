package com.ruoyi.googleSub.service.impl;

//
import com.ruoyi.googleSub.domain.dto.GooglePayDto;
import com.ruoyi.googleSub.domain.entity.CheckRequest;
import com.ruoyi.googleSub.domain.vo.CheckRequestVO;
import com.ruoyi.googleSub.service.sub.BasicRequestService;
import com.ruoyi.googleSub.service.sub.CheckProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/25 15:31
 */
@Service
public class CheckProductsServiceImpl implements CheckProductsService {

    @Autowired
    BasicRequestService basicRequestService;

//    @Autowired
//    VipProductsMapper vipProductsMapper;

    @Override
    public List<Map<String, String>> checkProductsOrder(CheckRequest checkProductRequest) {
        String packageName = checkProductRequest.getPackageName();
        List<Map<String, String>> res = new ArrayList<>();
        GooglePayDto googlePayDto = new GooglePayDto();
        for (CheckRequestVO iterm : checkProductRequest.getCheckProductsVOS()) {
            googlePayDto.setProductId(iterm.getProductId());
            googlePayDto.setPackageName(packageName);
            googlePayDto.setPurchaseToken(iterm.getPurchaseToken());
            res.add(basicRequestService.productsGoodsCheck(googlePayDto));
        }
        return res;
    }
}
