package com.aduner.dao;

import com.aduner.po.PoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<PoUser,Long> {
    PoUser findByUsernameAndPassword(String username,String password);
}
