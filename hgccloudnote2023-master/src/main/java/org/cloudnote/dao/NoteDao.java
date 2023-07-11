package org.cloudnote.dao;

import org.apache.ibatis.annotations.Param;
import org.cloudnote.entity.Note;

import java.util.List;
import java.util.Map;

public interface NoteDao {

    public int updateBookId(Map<String,Object> params);

    public int recycle(String id);

    /**
     * 更新笔记
     * @param note 修改的笔记对象
     * @return 更新多少数据
     */
    public int update(Note note);

    /**
     * 保存笔记
     * @param note 笔记对象
     */
    public void save(Note note);

    Note findById(String id);
    List<Note> findByBookId(String bookId);
}
