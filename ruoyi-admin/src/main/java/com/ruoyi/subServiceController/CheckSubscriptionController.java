package com.ruoyi.subServiceController;


import com.ruoyi.common.resp.BaseResponse;
import com.ruoyi.common.resp.ResultUtils;
import com.ruoyi.googleSub.domain.entity.CheckRequest;
import com.ruoyi.googleSub.service.sub.CheckSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/25 15:27
 */
@RestController
public class CheckSubscriptionController {

    @Autowired
    CheckSubscriptionService checkSubscriptionService;

    @PostMapping("/checkSubscriptionStatusAll")
    public BaseResponse<List<Map<String, String>>> checkSubscriptionStatusAll(@RequestBody CheckRequest checkRequest) throws IOException {
        return ResultUtils.success(checkSubscriptionService.checkSubscriptionOrder(checkRequest));
    }

}
