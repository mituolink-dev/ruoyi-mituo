package com.ruoyi.subServiceController;
import com.ruoyi.common.resp.BaseResponse;
import com.ruoyi.common.resp.ResultUtils;
import com.ruoyi.googleSub.domain.entity.CheckRequest;
import com.ruoyi.googleSub.service.sub.CheckProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/25 15:27
 */
@RestController
public class CheckProductsController {

    @Autowired
    CheckProductsService checkProductsService;

    @PostMapping("/checkProductsStatusAll")
    public BaseResponse<List<Map<String, String>>> checkProductsStatusAll(@RequestBody CheckRequest checkProductRequest) {
        return ResultUtils.success(checkProductsService.checkProductsOrder(checkProductRequest));
    }
}
