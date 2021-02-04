package com.aduner.web;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TypeShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listPublishBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(100));
        return "types";
    }

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model, @PathVariable("id") Long typeId,PoBlog blog) {
        if (typeId != null && typeId != 0) {
            blog.setType(typeService.getType(typeId));
        }
        model.addAttribute("page", blogService.listPublishBlog(pageable, blog));
        model.addAttribute("types", typeService.listTypeTop(100));
        model.addAttribute("typeId",typeId);
        return "types";
    }
}
