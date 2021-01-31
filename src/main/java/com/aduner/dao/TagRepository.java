package com.aduner.dao;

import com.aduner.po.PoTag;
import com.aduner.po.PoType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<PoTag,Long> {
    PoTag findByName(String name);
}
