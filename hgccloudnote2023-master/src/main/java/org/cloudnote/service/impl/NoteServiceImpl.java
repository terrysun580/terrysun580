package org.cloudnote.service.impl;

import org.cloudnote.dao.NoteDao;
import org.cloudnote.dao.ShareDao;
import org.cloudnote.entity.Note;
import org.cloudnote.entity.Share;
import org.cloudnote.service.NoteService;
import org.cloudnote.uitl.NoteResult;
import org.cloudnote.uitl.NoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDao noteDao;

    @Autowired
    private ShareDao shareDao;

    @Override
    public NoteResult moveNote(String noteId, String bookId) {
        NoteResult result = new NoteResult();
        Map<String,Object> params = new HashMap<>();
        params.put("notebook_id",bookId);
        params.put("note_id",noteId);

        noteDao.updateBookId(params);
        result.setStatus(0);
        result.setMsg("转移笔记成功");
        return result;
    }

    @Override
    public NoteResult loadShare(String shareId) {
        NoteResult result = new NoteResult();
        Share share = shareDao.findById(shareId);
        result.setStatus(0);
        result.setMsg("查询成功");
        result.setData(share);
        return result;
    }

    @Override
    public NoteResult searchShare(String keyword) {
        NoteResult result = new NoteResult();
        String title = "";
        if(!"".equals(keyword) && keyword != null){
            title = "%" + keyword +"%";
        }else{
            title = "%";
        }
        List<Share> list = shareDao.findLikeTitle(title);

        result.setStatus(0);
        result.setMsg("检索成功");
        result.setData(list);
        return result;
    }

    @Override
    public NoteResult shareNote(String noteId) {
        NoteResult result = new NoteResult();
        //检查是否分享过
        Share has_share = shareDao.findNoteId(noteId);
        if(has_share != null){
            result.setStatus(1);
            result.setMsg("该笔记已分享过");
            return result;
        }
        //未分享
        Note note = noteDao.findById(noteId);
        if(note == null){
            result.setStatus(2);
            result.setMsg("该笔记不存在");
            return result;
        }
        Share share = new Share();
        share.setCn_note_id(note.getCn_note_id());
        share.setCn_share_title(note.getCn_note_title());
        String shareId = NoteUtil.createId();
        share.setCn_share_id(shareId);
        share.setCn_share_body(note.getCn_note_body());
        //分享
        shareDao.save(share);

        result.setStatus(0);
        result.setMsg("分享笔记成功");
        return result;
    }

    @Override
    public NoteResult updateNote(String noteTitle, String noteBody, String noteId) {
        NoteResult result = new NoteResult();
        Note note = new Note();
        note.setCn_note_body(noteBody);
        note.setCn_note_title(noteTitle);
        note.setCn_note_id(noteId);
        long time = System.currentTimeMillis();
        note.setCn_note_last_modify_time(time);
        //更新笔记数据
        noteDao.update(note);

        result.setStatus(0);
        result.setMsg("更新笔记成功");
        return result;
    }

    @Override
    public NoteResult addNote(String bookId, String userId, String noteTitle) {
        NoteResult result = new NoteResult();
        Note note = new Note();
        note.setCn_notebook_id(bookId);
        note.setCn_user_id(userId);
        note.setCn_note_title(noteTitle);
        String noteId = NoteUtil.createId();//创建笔记id
        note.setCn_note_id(noteId);
        note.setCn_note_status_id("1");
        note.setCn_note_type_id("1");
        note.setCn_note_body("");
        long time = System.currentTimeMillis();//获取当前系统时间
        note.setCn_note_create_time(time);
        note.setCn_note_last_modify_time(time);

        noteDao.save(note);

        result.setStatus(0);
        result.setMsg("创建笔记成功");
        result.setData(noteId);
        return result;
    }

    @Override
    public NoteResult loadNoteDetail(String id) {
        NoteResult result = new NoteResult();
        Note note = noteDao.findById(id);
        if(note == null){
            result.setStatus(1);
            result.setData("找不到笔记信息");
            return result;
        }

        result.setData(note);
        result.setMsg("查找笔记成功");
        result.setStatus(0);
        return result;
    }

    @Override
    public NoteResult loadBookNotes(String bookId) {
        NoteResult result = new NoteResult();
        List<Note> list = noteDao.findByBookId(bookId);

        result.setStatus(0);
        result.setMsg("加载笔记列表成功");
        result.setData(list);
        return result;
    }
}
