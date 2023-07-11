package org.cloudnote.controller;

import org.cloudnote.service.BookService;
import org.cloudnote.uitl.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

///notebook/loadbooks.do
@Controller
@RequestMapping("/notebook")
public class BookController {
    @Autowired
    private BookService bookService;


    @ResponseBody
    @RequestMapping("/add.do")
    public NoteResult addNoteBook(String userId,String bookName){
        NoteResult result = bookService.addBook(userId, bookName);
        return result;
    }

    @ResponseBody
    @RequestMapping("/loadbooks.do")
    public NoteResult loadBooks(String userId){
        NoteResult result = bookService.loadUserBooks(userId);
        return result;
    }

}
