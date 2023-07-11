package org.cloudnote.dao.test;

import org.cloudnote.dao.NoteDao;
import org.cloudnote.dao.ShareDao;
import org.cloudnote.entity.Note;
import org.cloudnote.entity.Share;
import org.cloudnote.uitl.SpringTest;
import org.junit.Test;

import java.util.List;

public class TestShareDao extends SpringTest {
    //e53a7729-89b9-4e04-aff8-67d76cec7b3b
    @Test
    public void test4(){
        ShareDao dao = super.getContext().getBean("shareDao", ShareDao.class);
        Share byId = dao.findById("e53a7729-89b9-4e04-aff8-67d76cec7b3b");
        System.out.println("byId = " + byId);
    }
    @Test
    public void test3(){
        ShareDao dao = super.getContext().getBean("shareDao", ShareDao.class);
        List<Share> sh = dao.findLikeTitle("我的笔记1");
        System.out.println("sh = " + sh);
    }
    @Test
    public void test2(){
        ShareDao dao = super.getContext().getBean("shareDao", ShareDao.class);
        Share share = new Share();
        share.setCn_note_id("1111111111111111111");
        share.setCn_share_body("2222222222222222");
        share.setCn_share_id("444444444444444444444");
        share.setCn_share_title("3333333333333333333");
        dao.save(share);
    }

    @Test
    public void test1(){
        ShareDao dao = super.getContext().getBean("shareDao", ShareDao.class);
        Share share =   dao.findNoteId("b4f82f9f-bc0f-480a-b8f2-335164d69945");
        System.out.println("share = " + share);
    }



}
