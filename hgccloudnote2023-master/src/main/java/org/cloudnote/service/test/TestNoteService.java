package org.cloudnote.service.test;

import org.cloudnote.service.impl.BookServiceImpl;
import org.cloudnote.service.impl.NoteServiceImpl;
import org.cloudnote.uitl.NoteResult;
import org.cloudnote.uitl.SpringTest;
import org.junit.Test;

public class TestNoteService extends SpringTest {
    @Test
    public void test4(){
        NoteServiceImpl service =
                super.getContext().getBean("noteServiceImpl", NoteServiceImpl.class);
        NoteResult result = service.searchShare("1");
        System.out.println("result = " + result);
    }
    @Test
    public void test3(){
        NoteServiceImpl service =
                super.getContext().getBean("noteServiceImpl", NoteServiceImpl.class);
        //                         051538a6-0f8e-472c-8765-251a795bc88f
        NoteResult result = service.shareNote("051538a6-0f8e-472c-8765-251a795bc88f13213");
        System.out.println("result = " + result);
    }
    @Test
    public void test2(){
        NoteServiceImpl service =
                super.getContext().getBean("noteServiceImpl", NoteServiceImpl.class);
        NoteResult result = service.loadNoteDetail("046b0110-67f9-48c3-bef3-b0b23bda9d4e");
        System.out.println("result = " + result);
    }

    @Test
    public void test1(){
        NoteServiceImpl service =
            super.getContext().getBean("noteServiceImpl", NoteServiceImpl.class);
        NoteResult result = service.loadBookNotes("6d763ac9-dca3-42d7-a2a7-a08053095c08");
        System.out.println("result = " + result);
    }
}
