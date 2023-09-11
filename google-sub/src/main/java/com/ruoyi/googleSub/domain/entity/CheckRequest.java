package com.ruoyi.googleSub.domain.entity;

import com.ruoyi.googleSub.domain.vo.CheckRequestVO;
import lombok.Data;

import java.util.List;

/**
 * @program:
 * @version: 1.0
 * @author: huijia
 * @create: 2023/8/25 15:25
 */
@Data
public class CheckRequest {
    private String packageName;
    private List<CheckRequestVO> checkProductsVOS;
}
