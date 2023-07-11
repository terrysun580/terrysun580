package org.cloudnote.dao;

import org.cloudnote.entity.Share;

import java.util.List;

public interface ShareDao {

    public Share findById(String id);
    //通过标题模糊查询共享笔记
    public List<Share> findLikeTitle(String title);

    public Share findNoteId(String noteId);

    public void save(Share share);

}
