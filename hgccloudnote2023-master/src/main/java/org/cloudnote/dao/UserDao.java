package org.cloudnote.dao;

import org.cloudnote.entity.User;

public interface UserDao {

    public User findByName(String name);

    public void save(User user);
}
