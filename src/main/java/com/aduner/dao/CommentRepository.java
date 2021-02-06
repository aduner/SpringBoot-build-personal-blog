package com.aduner.dao;

import com.aduner.po.PoComment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<PoComment,Long> {
    List<PoComment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

    PoComment findOneById(Long parentCommentId);
}
