package com.aduner.service;

import com.aduner.po.PoComment;

import java.util.List;

public interface CommentService {

    List<PoComment> listCommentByBlogId(Long blogId);

    PoComment saveComment(PoComment comment);
}