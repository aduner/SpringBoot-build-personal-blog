package com.aduner.service;

import com.aduner.dao.UserRepository;
import com.aduner.po.PoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserRepository userRepository;

    @Override
    public PoUser checkUser(String username, String password) {
        username = username.strip();
        password = DigestUtils.md5DigestAsHex((password + username).getBytes());
        PoUser poUser = userRepository.findByUsernameAndPassword(username, password);
        return poUser;
    }
}
