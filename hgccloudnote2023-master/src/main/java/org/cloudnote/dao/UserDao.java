package org.cloudnote.dao;

import org.cloudnote.entity.User;

public interface UserDao {

    public User findByName(String name);
    public User findById(String id);
    public void changePassword(User user);
    public void save(User user);
}
