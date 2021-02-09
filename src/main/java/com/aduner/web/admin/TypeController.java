package com.aduner.web.admin;

import com.aduner.po.PoType;
import com.aduner.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 分类控制器
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    /**
     * 跳转至管理types分类页面
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String blogTypes(
            @PageableDefault(
                    size = 10,
                    sort = {"id"},
                    direction = Sort.Direction.DESC)
                    Pageable pageable,
            Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/blog_types";
    }

    /**
     * 添加分类
     * @param type
     * @param attributes
     * @return
     */
    @PostMapping("/types/add")
    public String addType(PoType type, RedirectAttributes attributes) {
        type.setName(type.getName().strip());
        if (typeService.getTagByName(type.getName())!= null) {
            attributes.addFlashAttribute("error","该分类已存在");
            return "redirect:/admin/types";
        }
        PoType t = typeService.saveType(type);
        return "redirect:/admin/types";
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @GetMapping("/types/delete")
    public String typesDelete(@RequestParam("id") Long id) {
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }
}
