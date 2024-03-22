package com.ruoyi.web.controller.admin;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruoyi.common.core.domain.entity.SysUser;
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
import com.ruoyi.system.domain.TbFeescheme;
import com.ruoyi.system.service.ITbFeeschemeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收费方案管理Controller
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
@RestController
@RequestMapping("/admin/feescheme")
public class TbFeeschemeController extends BaseController
{
    @Autowired
    private ITbFeeschemeService tbFeeschemeService;

    /**
     * 查询收费方案管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:feescheme:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbFeescheme tbFeescheme)
    {
        startPage();
        List<TbFeescheme> list = tbFeeschemeService.selectTbFeeschemeList(tbFeescheme);
        return getDataTable(list);
    }

    /**
     * 导出收费方案管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:feescheme:export')")
    @Log(title = "收费方案管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbFeescheme tbFeescheme)
    {
        List<TbFeescheme> list = tbFeeschemeService.selectTbFeeschemeList(tbFeescheme);
        ExcelUtil<TbFeescheme> util = new ExcelUtil<TbFeescheme>(TbFeescheme.class);
        util.exportExcel(response, list, "收费方案管理数据");
    }

    /**
     * 获取收费方案管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:feescheme:query')")
    @GetMapping(value = "/{fsmid}")
    public AjaxResult getInfo(@PathVariable("fsmid") Long fsmid)
    {
        return success(tbFeeschemeService.selectTbFeeschemeByFsmid(fsmid));
    }

    /**
     * 新增收费方案管理
     */
    @PreAuthorize("@ss.hasPermi('admin:feescheme:add')")
    @Log(title = "收费方案管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbFeescheme tbFeescheme, HttpSession session)
    {
        SysUser user=(SysUser)session.getAttribute("user");
        tbFeescheme.setFounder(user.getNickName());
        tbFeescheme.setAddtime(new Date());
        tbFeescheme.setCreationtime(new Date());
        return toAjax(tbFeeschemeService.insertTbFeescheme(tbFeescheme));
    }

    /**
     * 修改收费方案管理
     */
    @PreAuthorize("@ss.hasPermi('admin:feescheme:edit')")
    @Log(title = "收费方案管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbFeescheme tbFeescheme)
    {
        return toAjax(tbFeeschemeService.updateTbFeescheme(tbFeescheme));
    }

    /**
     * 删除收费方案管理
     */
    @PreAuthorize("@ss.hasPermi('admin:feescheme:remove')")
    @Log(title = "收费方案管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fsmids}")
    public AjaxResult remove(@PathVariable Long[] fsmids)
    {
        return toAjax(tbFeeschemeService.deleteTbFeeschemeByFsmids(fsmids));
    }
}
