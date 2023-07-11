package org.cloudnote.dao.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.cloudnote.dao.UserDao;
import org.cloudnote.entity.User;
import org.cloudnote.uitl.NoteUtil;
import org.cloudnote.uitl.SpringTest;
import org.junit.Test;

import javax.sql.DataSource;

public class TestUserDao extends SpringTest {
    @Test
    public void test4(){
        UserDao dao = super.getContext().getBean("userDao", UserDao.class);
        User user = new User();
        user.setCn_user_name("lilei");
        user.setCn_user_nick("lei");
        String md5_password = NoteUtil.md5("123");
        user.setCn_user_password(md5_password);
        user.setCn_user_token(null);
        String userId = NoteUtil.createId();
        user.setCn_user_id(userId);
        dao.save(user);
    }
    @Test
    public void test3(){
        UserDao dao = super.getContext().getBean("userDao", UserDao.class);
        User zhouj = dao.findByName("zhouj");
        System.out.println("zhouj = " + zhouj);
    }

    @Test
    public void test2(){
        SqlSessionFactory fs = super.getContext().getBean("sqlSessionFactory", SqlSessionFactory.class);
        System.out.println("fs = " + fs);
    }

    @Test
    public void test1(){
        DataSource ds = super.getContext().getBean("dbcp", DataSource.class);
        System.out.println("ds = " + ds);
    }
}
