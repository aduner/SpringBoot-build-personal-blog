package com.aduner.web.admin;

import com.aduner.po.PoBlog;
import com.aduner.service.BlogService;
import com.aduner.service.TypeService;
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

/**
 * 后台blog控制器
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    /**
     * 展示blog
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(
                        size = 10,
                        sort = {"updateTime"},
                        direction = Sort.Direction.DESC) Pageable pageable,
                        PoBlog blog,
                        Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blog_list";
    }

    /**
     * 删除blog
     * @param id
     * @return
     */
    @PostMapping("/blog/delete")
    public String delete(@RequestParam("id") Long id){
        blogService.deleteBlog(id);
        return  "redirect:/admin/blogs";
    }

    /**
     * 搜索blog
     * @param pageable
     * @param blog
     * @param model
     * @param typeId
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(
            @PageableDefault(
                    size = 8,
                    sort = {"updateTime"},
                    direction = Sort.Direction.DESC) Pageable pageable,
            PoBlog blog,
            Model model,
            @RequestParam("typeId") Long typeId) {
        if(typeId!=0)
            blog.setType(typeService.getType(typeId));
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blog_list :: blogList";
    }

}
