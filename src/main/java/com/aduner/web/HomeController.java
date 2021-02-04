package com.aduner.web;

import com.aduner.po.PoBlog;
import com.aduner.po.PoTag;
import com.aduner.po.PoType;
import com.aduner.service.BlogService;
import com.aduner.service.TagService;
import com.aduner.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, PoBlog blog,
                       Model model) {
        model.addAttribute("page", blogService.listPublishBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }

    @RequestMapping("/search")
    public String search(
            @PageableDefault(
                    size = 8,
                    sort = {"updateTime"},
                    direction = Sort.Direction.DESC) Pageable pageable,
            PoBlog blog,
            Model model,
            @RequestParam("typeId") Long typeId,
            @RequestParam("tagId") Long tagId,
            HttpServletRequest httpServletRequest) {
        if (typeId != null && typeId != 0) {
            blog.setType(typeService.getType(typeId));
        }
        if (tagId != null && tagId != 0) {
            model.addAttribute("page", blogService.listTagBlog(pageable, tagId));
        } else {
            model.addAttribute("page", blogService.listPublishBlog(pageable, blog));
        }
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        model.addAttribute("searchFlag",true);

        if (httpServletRequest.getMethod().equals("POST")) {
            return "index :: div-container";
        } else {
            model.addAttribute("pageTemplates",httpServletRequest.getRequestURL() +"?"+ httpServletRequest.getQueryString().replaceAll("&page=.+","")+"&page=");
            return "index";
        }
    }

}
