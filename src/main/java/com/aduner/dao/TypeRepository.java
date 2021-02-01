package com.aduner.dao;

import com.aduner.po.PoType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<PoType,Long> {
    PoType findByName(String name);
    @Query("select t from PoType t ")
    List<PoType> findTop(Pageable pageable);
}
