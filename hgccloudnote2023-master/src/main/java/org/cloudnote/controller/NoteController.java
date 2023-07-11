package org.cloudnote.controller;

import org.cloudnote.service.NoteService;
import org.cloudnote.uitl.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @ResponseBody
    @RequestMapping("/move.do")
    public NoteResult moveNote(String noteId,String bookId){
        NoteResult result = noteService.moveNote(noteId, bookId);
        return result;
    }

// http://localhost/note/update.do?noteTitle=0000000000&noteBody=1111111111111&noteId=22222222222222222222222222222222222
    @ResponseBody
    @RequestMapping("/update.do")
    public NoteResult  updateNote(String noteTitle, String noteBody, String noteId){
        NoteResult result = noteService.updateNote(noteTitle,noteBody,noteId);
        return result;
    }

// http://localhost:80/note/add.do?bookId=0000000000&userId=1111111111111&noteTitle=todayis708
    @ResponseBody
    @RequestMapping("/add.do")
    public NoteResult  addNote(String bookId, String userId, String noteTitle){
        NoteResult result = noteService.addNote(bookId,userId,noteTitle);
        return result;
    }
//http://localhost:80/note/load.do?id=046b0110-67f9-48c3-bef3-b0b23bda9d4e
    @ResponseBody
    @RequestMapping("/load.do")
    public NoteResult  loadNoteDetail(String id){
        NoteResult result = noteService.loadNoteDetail(id);
        return result;
    }


//  http://localhost:80/note/loadnotes.do?bookId=6d763ac9-dca3-42d7-a2a7-a08053095c08
    //地址栏直接输入，a标签都是get请求；
    @ResponseBody
    @RequestMapping("/loadnotes.do")
    //@GetMapping
    //@PostMapping
    public NoteResult loadNotes(String bookId){
        NoteResult result = noteService.loadBookNotes(bookId);
        return result;
    }
}
