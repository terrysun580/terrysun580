package org.cloudnote.dao.test;

import org.cloudnote.dao.BookDao;
import org.cloudnote.dao.NoteDao;
import org.cloudnote.entity.Note;
import org.cloudnote.entity.NoteBook;
import org.cloudnote.uitl.SpringTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestNoteDao extends SpringTest {
    @Test
    public void test6(){
        NoteDao dao = super.getContext().getBean("noteDao", NoteDao.class);
        Map<String,Object> map = new HashMap<>();
        map.put("notebook_id","1b05c9c7-f1cd-4842-beae-15ed95a1febb");
        map.put("note_id","fd57fa68-5897-4676-9cdd-91cf85101acc");
       dao.updateBookId(map);
    }
    @Test
    public void test5(){
        NoteDao dao = super.getContext().getBean("noteDao", NoteDao.class);
        dao.recycle("22222222222222222222222222222222222");
    }
    @Test
    public void test4(){
        NoteDao dao = super.getContext().getBean("noteDao", NoteDao.class);
        Note note = new Note();
        note.setCn_note_id("22222222222222222222222222222222222");
        note.setCn_note_last_modify_time(System.currentTimeMillis());
        note.setCn_note_title("title");
        note.setCn_note_body("body11111111111");
        dao.update(note);
        //System.out.println("update = " + update);
                
    }
    @Test
    public void test3(){
        NoteDao dao = super.getContext().getBean("noteDao", NoteDao.class);
        Note note = new Note();
        note.setCn_note_body("1111111111111111");
        note.setCn_note_create_time(10000000L);
        note.setCn_note_status_id("222222222222");
        note.setCn_notebook_id("3333333333333333");
        note.setCn_note_id("11111111111111");
        note.setCn_user_id("000000000000000000000000");
        note.setCn_note_title("ttttttttttttttttttttt");
        dao.save(note);
    }
    @Test
    public void test2(){
        NoteDao dao = super.getContext().getBean("noteDao", NoteDao.class);
        Note note = dao.findById("046b0110-67f9-48c3-bef3-b0b23bda9d4e");
        System.out.println("note = " + note);
    }

    @Test
    public void test1(){
        NoteDao dao = super.getContext().getBean("noteDao", NoteDao.class);
        List<Note> notes = dao.findByBookId("6d763ac9-dca3-42d7-a2a7-a08053095c08");
        System.out.println("notes = " + notes);
    }



}
