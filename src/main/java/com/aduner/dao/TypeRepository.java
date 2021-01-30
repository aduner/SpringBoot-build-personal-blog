package com.aduner.dao;

import com.aduner.po.PoType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<PoType,Long> {
    PoType findByName(String name);
}
