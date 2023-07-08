package com.manit.scs.demo.dao;

import com.manit.scs.demo.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
