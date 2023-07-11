package org.cloudnote.controller;

import org.cloudnote.service.UserService;
import org.cloudnote.uitl.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Autowired//依赖注入
    private UserService userService;

    @ResponseBody
    @RequestMapping("regist.do")
    public NoteResult register(String name,String nick,String password){
        NoteResult result = userService.regist(name,nick,password);
        return result;
    }



    //spring通过方法处理请求
    //http://localhost:80/user/login.do
    @ResponseBody //将响应的数据以json返回
    @RequestMapping("/login.do")//{"name":name,"password":password}
    public NoteResult execute(String name, String password){
        //System.out.println("name = " + name);
        //System.out.println("password = " + password);
        NoteResult result = userService.checkLogin(name, password);
        return result;
    }
}
