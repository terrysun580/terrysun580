package org.cloudnote.dao.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.cloudnote.dao.BookDao;
import org.cloudnote.dao.UserDao;
import org.cloudnote.entity.NoteBook;
import org.cloudnote.entity.User;
import org.cloudnote.uitl.NoteUtil;
import org.cloudnote.uitl.SpringTest;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

public class TestBookDao extends SpringTest {
    @Test
    public void test2(){
        BookDao dao = super.getContext().getBean("bookDao", BookDao.class);
        NoteBook book = new NoteBook();
        book.setCn_notebook_id("11111111111111111");
        book.setCn_user_id("1111111111111111111");
        book.setCn_notebook_name("bbbbbbbbbbbbbbbbb");
        book.setCn_user_id("wewq12321321ewrwre");
        book.setCn_notebook_desc("desc");
        book.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));

        dao.save(book);
    }
    @Test
    public void test3(){
        BookDao dao = super.getContext().getBean("bookDao", BookDao.class);
        List<NoteBook> byUserId = dao.findByUserId("03590914-a934-4da9-ba4d-b41799f917d1");
        System.out.println("byUserId = " + byUserId);
    }



}
