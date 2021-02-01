package com.aduner.dao;

import com.aduner.po.PoTag;
import com.aduner.po.PoType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<PoTag,Long> {
    PoTag findByName(String name);
    @Query("select t from PoTag t ")
    List<PoTag> findTop(Pageable pageable);
}
