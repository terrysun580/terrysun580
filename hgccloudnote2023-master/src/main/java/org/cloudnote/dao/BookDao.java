package org.cloudnote.dao;

import org.cloudnote.entity.NoteBook;

import java.util.List;

public interface BookDao {

    public void save(NoteBook book);
    //根据用户id查询当前用户笔记本信息  NoteBookMapper.xml
    public List<NoteBook> findByUserId(String userId);
}
