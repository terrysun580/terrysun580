package org.cloudnote.service.impl;

import org.cloudnote.dao.BookDao;
import org.cloudnote.entity.NoteBook;
import org.cloudnote.service.BookService;
import org.cloudnote.uitl.NoteResult;
import org.cloudnote.uitl.NoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public NoteResult addBook(String userId, String bookName) {
        NoteResult result = new NoteResult();
        NoteBook book = new NoteBook();
        book.setCn_user_id(userId);
        String bookId = NoteUtil.createId();
        book.setCn_notebook_id(bookId);
        book.setCn_notebook_type_id("5"); //normal
        book.setCn_notebook_desc("");
        book.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
        book.setCn_notebook_name(bookName);
        bookDao.save(book);

        result.setStatus(0);
        result.setMsg("添加笔记本成功");
        result.setData(bookId);
        return result;
    }

    @Override
    public NoteResult loadUserBooks(String userId) {
        NoteResult result = new NoteResult();
        List<NoteBook> books = bookDao.findByUserId(userId);

        result.setStatus(0);
        result.setMsg("加载用户笔记本成功");
        result.setData(books);
        return result;
    }
}
