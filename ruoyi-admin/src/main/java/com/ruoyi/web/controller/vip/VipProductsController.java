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
import com.ruoyi.system.domain.VipProducts;
import com.ruoyi.system.service.IVipProductsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会员内购信息Controller
 * 
 * @author ruoyi
 * @date 2023-09-11
 */
@RestController
@RequestMapping("/vip/products")
public class VipProductsController extends BaseController
{
    @Autowired
    private IVipProductsService vipProductsService;

    /**
     * 查询会员内购信息列表
     */
    @PreAuthorize("@ss.hasPermi('vip:products:list')")
    @GetMapping("/list")
    public TableDataInfo list(VipProducts vipProducts)
    {
        startPage();
        List<VipProducts> list = vipProductsService.selectVipProductsList(vipProducts);
        return getDataTable(list);
    }

    /**
     * 导出会员内购信息列表
     */
    @PreAuthorize("@ss.hasPermi('vip:products:export')")
    @Log(title = "会员内购信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VipProducts vipProducts)
    {
        List<VipProducts> list = vipProductsService.selectVipProductsList(vipProducts);
        ExcelUtil<VipProducts> util = new ExcelUtil<VipProducts>(VipProducts.class);
        util.exportExcel(response, list, "会员内购信息数据");
    }

    /**
     * 获取会员内购信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('vip:products:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vipProductsService.selectVipProductsById(id));
    }

    /**
     * 新增会员内购信息
     */
    @PreAuthorize("@ss.hasPermi('vip:products:add')")
    @Log(title = "会员内购信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipProducts vipProducts)
    {
        return toAjax(vipProductsService.insertVipProducts(vipProducts));
    }

    /**
     * 修改会员内购信息
     */
    @PreAuthorize("@ss.hasPermi('vip:products:edit')")
    @Log(title = "会员内购信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipProducts vipProducts)
    {
        return toAjax(vipProductsService.updateVipProducts(vipProducts));
    }

    /**
     * 删除会员内购信息
     */
    @PreAuthorize("@ss.hasPermi('vip:products:remove')")
    @Log(title = "会员内购信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vipProductsService.deleteVipProductsByIds(ids));
    }
}
