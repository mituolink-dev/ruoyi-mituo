package com.ruoyi.googleSub.service.sub;


import com.ruoyi.googleSub.domain.entity.CheckRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/25 15:28
 */
public interface CheckSubscriptionService {
    public List<Map<String, String>> checkSubscriptionOrder(CheckRequest checkProductRequest) throws IOException;
}
