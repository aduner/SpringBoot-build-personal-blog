package com.aduner.web.admin;

import com.aduner.po.PoBlog;
import com.aduner.po.PoTag;
import com.aduner.po.PoType;
import com.aduner.po.PoUser;
import com.aduner.service.BlogService;
import com.aduner.service.TagService;
import com.aduner.service.TypeService;
import com.aduner.util.ExtractDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Blog编辑控制器
 */
@Controller
@RequestMapping("/admin")
public class EditorController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    /**
     * 新建blog，转到blog编辑页面
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String newBlog(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        return "admin/new_blog";
    }

    /**
     * 更新blog，转到blog编辑页面，自动填入已有内容
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/update")
    public String updateBlog(@RequestParam("id") Long id, Model model) {
        PoBlog blog = blogService.getBlog(id);
        PoType type = blog.getType();
        List<PoTag> tags = blog.getTags();
        String tagIds = "";
        for (PoTag tag : tags) {
            tagIds += "," + tag.getId();
        }
        if( tagIds.contains(","))
            tagIds = tagIds.substring(1);
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", blog);
        model.addAttribute("type", type);
        model.addAttribute("tagIds", tagIds);
        return "admin/update_blog";
    }

    /**
     * 编辑blog，对博客对新建或更改进行处理
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/editor")
    public String editor(PoBlog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((PoUser) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        blog.setDescription(ExtractDescription.extract(blog.getDescription()));
        PoBlog b;
        if (blog.getId() == null) {
            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blogs";

    }
}
