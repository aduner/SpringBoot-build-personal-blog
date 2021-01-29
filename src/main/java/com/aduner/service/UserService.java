package com.aduner.service;

import com.aduner.po.PoUser;

public interface UserService {
    PoUser checkUser(String username,String password);
}
