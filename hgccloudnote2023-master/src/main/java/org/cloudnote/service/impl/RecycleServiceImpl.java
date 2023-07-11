package org.cloudnote.service.impl;

import org.cloudnote.dao.NoteDao;
import org.cloudnote.service.RecycleService;
import org.cloudnote.uitl.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecycleServiceImpl implements RecycleService {

    @Autowired
    private NoteDao noteDao;

    @Override
    public NoteResult recyceleNote(String noteId) {
        NoteResult result = new NoteResult();
        noteDao.recycle(noteId);
        result.setStatus(0);
        result.setMsg("笔记已成功放入回收站");
        return result;
    }
}
