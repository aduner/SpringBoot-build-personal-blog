package com.aduner.service;

import com.aduner.po.PoBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    PoBlog getBlog(Long id);

    Page<PoBlog> listBlog(Pageable pageable, PoBlog blog);

    Page<PoBlog> listPublishBlog(Pageable pageable, PoBlog blog);

    Page<PoBlog> listPublishBlog(Pageable pageable);

    Page<PoBlog> listTagBlog(Pageable pageable,Long id);

    PoBlog saveBlog(PoBlog blog);

    PoBlog updateBlog(Long id, PoBlog blog);

    void deleteBlog(Long id);

    List<PoBlog> listRecommendBlogTop(Integer size);

    PoBlog getAndConvert(Long id);
}
