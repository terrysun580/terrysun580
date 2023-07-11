package org.cloudnote.controller;

import org.cloudnote.service.RecycleService;
import org.cloudnote.uitl.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/recycle")
public class RecycleController {

    @Autowired
    private RecycleService recycleService;

    @ResponseBody
    @RequestMapping("/recycle.do")
    public NoteResult execute(String noteId){
        System.out.println("noteId = " + noteId);
        NoteResult resutlt = recycleService.recyceleNote(noteId);
        return resutlt;
    }
}
