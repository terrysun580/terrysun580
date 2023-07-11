package org.cloudnote.controller;

import org.cloudnote.service.NoteService;
import org.cloudnote.uitl.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class ShareNoteController {

    @Autowired
    private NoteService noteService;

    @ResponseBody
    @RequestMapping("/loadShare.do")
    public NoteResult loadShare(String shareId){
        NoteResult result = noteService.loadShare(shareId);
        return result;
    }

    @ResponseBody
    @RequestMapping("/search.do")
    public NoteResult searchShare(String keyword){
        NoteResult result = noteService.searchShare(keyword);
        return result;
    }

    @ResponseBody
    @RequestMapping("/share.do")
    public NoteResult execute(String noteId){
        NoteResult result = noteService.shareNote(noteId);
        return result;
    }
}
