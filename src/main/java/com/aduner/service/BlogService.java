package com.aduner.service;

import com.aduner.po.PoBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    PoBlog getBlog(Long id);

    Page<PoBlog> listBlog(Pageable pageable, PoBlog blog);

    PoBlog saveBlog(PoBlog blog);

    PoBlog updateBlog(Long id,PoBlog blog);

    void deleteBlog(Long id);
}
