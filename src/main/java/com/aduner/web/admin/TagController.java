package com.aduner.web.admin;

import com.aduner.po.PoTag;
import com.aduner.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 标签控制器
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 跳转至管理标签页
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String blogTags(
            @PageableDefault(
                    size = 10,
                    sort = {"id"},
                    direction = Sort.Direction.DESC)
                    Pageable pageable,
            Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/blog_tags";
    }

    /**
     * 添加标签
     * @param tag
     * @param attributes
     * @return
     */
    @PostMapping("/tags/add")
    public String addTag(PoTag tag, RedirectAttributes attributes) {
        tag.setName(tag.getName().strip());
        if (tagService.getTagByName(tag.getName())!= null) {
            attributes.addFlashAttribute("error","该标签已存在");
            return "redirect:/admin/tags";
        }
        PoTag t = tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @GetMapping("/tags/delete")
    public String tagsDelete(@RequestParam("id") Long id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }
}
