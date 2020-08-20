package com.ruoyi.web.controller.tinyUrl;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.tinyUrl.service.TinyUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 短链接查看处理
 *
 * @author zx
 */
@Controller
@RequestMapping("/view")
public class TinyUrlViewController extends BaseController {

    @Autowired
    private TinyUrlService tinyUrlService;

    /**
     * 查看短链接
     */
    @Log(title = "查看短链接", businessType = BusinessType.DELETE)
    @GetMapping("/{url}")
    public String view(@PathVariable String url, HttpServletResponse response) {
        Object view = tinyUrlService.view(url);
        if (ObjectUtils.isEmpty(view)) {
            return "/404.html";
        }
        return redirect(view.toString());
    }
}
