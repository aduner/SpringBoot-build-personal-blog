package com.aduner.web;

import com.aduner.po.PoBlog;
import com.aduner.service.BlogService;
import com.aduner.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TagShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listPublishBlog(pageable));
        model.addAttribute("tags", tagService.listTagTop(100));
        return "tags";
    }

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model, @PathVariable("id") Long tagId) {
        if (tagId != null && tagId != 0) {
            model.addAttribute("page", blogService.listTagBlog(pageable, tagId));
        } else {
            model.addAttribute("page", blogService.listPublishBlog(pageable));
        }
        model.addAttribute("tags", tagService.listTagTop(100));
        model.addAttribute("tagId", tagId);
        return "tags";
    }
}
