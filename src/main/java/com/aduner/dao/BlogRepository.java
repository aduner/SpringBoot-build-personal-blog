package com.aduner.dao;

import com.aduner.po.PoBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


public interface BlogRepository extends JpaRepository<PoBlog,Long>, JpaSpecificationExecutor<PoBlog> {
}
