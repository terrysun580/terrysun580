package org.cloudnote.service.test;

import org.cloudnote.service.UserService;
import org.cloudnote.service.impl.BookServiceImpl;
import org.cloudnote.uitl.NoteResult;
import org.cloudnote.uitl.SpringTest;
import org.junit.Test;

public class TestBookService extends SpringTest {

    @Test
    public void test1(){
        BookServiceImpl service =
            super.getContext().getBean("bookServiceImpl", BookServiceImpl.class);
        NoteResult result = service.loadUserBooks("03590914-a934-4da9-ba4d-b41799f917d1");
        System.out.println("result = " + result);
    }
}
