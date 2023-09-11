package com.ruoyi.web.controller.vip;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.VipSubscription;
import com.ruoyi.system.service.IVipSubscriptionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会员订阅信息Controller
 * 
 * @author ruoyi
 * @date 2023-09-11
 */
@RestController
@RequestMapping("/vip/subscription")
public class VipSubscriptionController extends BaseController
{
    @Autowired
    private IVipSubscriptionService vipSubscriptionService;

    /**
     * 查询会员订阅信息列表
     */
    @PreAuthorize("@ss.hasPermi('vip:subscription:list')")
    @GetMapping("/list")
    public TableDataInfo list(VipSubscription vipSubscription)
    {
        startPage();
        List<VipSubscription> list = vipSubscriptionService.selectVipSubscriptionList(vipSubscription);
        return getDataTable(list);
    }

    /**
     * 导出会员订阅信息列表
     */
    @PreAuthorize("@ss.hasPermi('vip:subscription:export')")
    @Log(title = "会员订阅信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VipSubscription vipSubscription)
    {
        List<VipSubscription> list = vipSubscriptionService.selectVipSubscriptionList(vipSubscription);
        ExcelUtil<VipSubscription> util = new ExcelUtil<VipSubscription>(VipSubscription.class);
        util.exportExcel(response, list, "会员订阅信息数据");
    }

    /**
     * 获取会员订阅信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('vip:subscription:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vipSubscriptionService.selectVipSubscriptionById(id));
    }

    /**
     * 新增会员订阅信息
     */
    @PreAuthorize("@ss.hasPermi('vip:subscription:add')")
    @Log(title = "会员订阅信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipSubscription vipSubscription)
    {
        return toAjax(vipSubscriptionService.insertVipSubscription(vipSubscription));
    }

    /**
     * 修改会员订阅信息
     */
    @PreAuthorize("@ss.hasPermi('vip:subscription:edit')")
    @Log(title = "会员订阅信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipSubscription vipSubscription)
    {
        return toAjax(vipSubscriptionService.updateVipSubscription(vipSubscription));
    }

    /**
     * 删除会员订阅信息
     */
    @PreAuthorize("@ss.hasPermi('vip:subscription:remove')")
    @Log(title = "会员订阅信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vipSubscriptionService.deleteVipSubscriptionByIds(ids));
    }
}
