package com.aduner.dao;

import com.aduner.po.PoBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BlogRepository extends JpaRepository<PoBlog,Long>, JpaSpecificationExecutor<PoBlog> {
    @Query("select b from PoBlog b where b.published = true")
    Page<PoBlog> findPublish(Pageable pageable);

    @Query("select b from PoBlog b where b.recommend = true and b.published=true")
    List<PoBlog> findTop(Pageable pageable);

    @Query("select b from PoBlog b where b.title like ?1 or b.content like ?1")
    Page<PoBlog> findByQuery(String query, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update PoBlog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);
}
