package org.cloudnote.service.test;

import org.cloudnote.service.UserService;
import org.cloudnote.uitl.NoteResult;
import org.cloudnote.uitl.SpringTest;
import org.junit.Test;

import javax.swing.*;

public class TestCheckLogin extends SpringTest {
    @Test
    public void test2(){
        UserService userService =
                super.getContext().getBean("userServiceImpl", UserService.class);
        NoteResult noteResult = userService.regist("zhouj123", "z","123");
        System.out.println("noteResult = " + noteResult);
    }
    @Test
    public void test1(){
        UserService userService =
            super.getContext().getBean("userServiceImpl", UserService.class);
        NoteResult noteResult = userService.checkLogin("zhouj", "123456");
        System.out.println("noteResult = " + noteResult);
    }
}
