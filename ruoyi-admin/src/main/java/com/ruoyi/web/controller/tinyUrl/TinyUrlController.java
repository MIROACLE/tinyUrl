package com.ruoyi.web.controller.tinyUrl;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.tinyUrl.domain.vo.TinyUrlVo;
import com.ruoyi.tinyUrl.service.TinyUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接操作处理
 *
 * @author zx
 */
@RestController
@RequestMapping("/tiny/url")
public class TinyUrlController extends BaseController {

    @Autowired
    private TinyUrlService tinyUrlService;

    /**
     * 获取短链接列表
     */
    @PreAuthorize("@ss.hasPermi('tiny:url:list')")
    @GetMapping("/list")
    public TableDataInfo list(TinyUrlVo tinyUrlvo) {
        startPage();
        List<TinyUrlVo> list = tinyUrlService.selectTinyUrlList(tinyUrlvo);
        return getDataTable(list);
    }

    /**
     * 生成短链接
     */
    @PreAuthorize("@ss.hasPermi('tiny:url:create')")
    @Log(title = "生成短链接", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult create(@Validated @RequestBody TinyUrlVo tinyUrlvo) {
        tinyUrlvo.setCreateBy(SecurityUtils.getUsername());
        return AjaxResult.success(tinyUrlService.createTinyUrl(tinyUrlvo));
    }

    /**
     * 删除短链接
     */
    @PreAuthorize("@ss.hasPermi('tiny:url:remove')")
    @Log(title = "删除短链接", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeId}")
    public AjaxResult remove(@PathVariable Long noticeId) {
        return toAjax(tinyUrlService.removeUrlById(noticeId));
    }


}
