package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.VipOrder;
import com.ruoyi.system.service.IVipOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 会员订单Controller
 * 
 * @author ruoyi
 * @date 2023-08-24
 */
@RestController
@RequestMapping("/system/order")
public class VipOrderController extends BaseController
{
    @Autowired
    private IVipOrderService vipOrderService;

    /**
     * 查询会员订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(VipOrder vipOrder)
    {
        startPage();
        List<VipOrder> list = vipOrderService.selectVipOrderList(vipOrder);
        return getDataTable(list);
    }

    /**
     * 导出会员订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "会员订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VipOrder vipOrder)
    {
        List<VipOrder> list = vipOrderService.selectVipOrderList(vipOrder);
        ExcelUtil<VipOrder> util = new ExcelUtil<VipOrder>(VipOrder.class);
        util.exportExcel(response, list, "会员订单数据");
    }

    /**
     * 获取会员订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vipOrderService.selectVipOrderById(id));
    }

    /**
     * 新增会员订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "会员订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipOrder vipOrder)
    {
        return toAjax(vipOrderService.insertVipOrder(vipOrder));
    }

    /**
     * 修改会员订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "会员订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipOrder vipOrder)
    {
        return toAjax(vipOrderService.updateVipOrder(vipOrder));
    }

    /**
     * 删除会员订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "会员订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vipOrderService.deleteVipOrderByIds(ids));
    }
}
