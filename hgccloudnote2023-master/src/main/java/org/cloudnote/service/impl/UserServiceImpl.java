package org.cloudnote.service.impl;

import org.cloudnote.dao.UserDao;
import org.cloudnote.entity.User;
import org.cloudnote.service.UserService;
import org.cloudnote.uitl.NoteResult;
import org.cloudnote.uitl.NoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public NoteResult regist(String name, String nick, String password) {
        NoteResult result = new NoteResult();
        //数据检测
        User has_user = userDao.findByName(name);
        if(has_user != null){
            result.setStatus(1);
            result.setMsg("用户名被占用");
            return result;
        }
        //将参数封装成对象保存
        User user = new User();
        user.setCn_user_name(name);
        user.setCn_user_nick(nick);
        String md5_password = NoteUtil.md5(password);
        user.setCn_user_password(md5_password);
        user.setCn_user_token(null);
        String userId = NoteUtil.createId();
        user.setCn_user_id(userId);
        //使用save方法将user存储到数据库
        userDao.save(user);

        result.setStatus(0);
        result.setMsg("注册成功");
        return result;
    }

    @Override
    public NoteResult checkLogin(String name, String password) {
        NoteResult result = new NoteResult();
        //根据用户名获取用户
        User user =  userDao.findByName(name);
        if(user == null){
            result.setStatus(1);
            result.setMsg("用户不存在");
            return result;
        }
        //检查密码
        String md5_password = NoteUtil.md5(password);
        //System.out.println("md5_password = " + md5_password);
        //System.out.println("user.getCn_user_password() = " + user.getCn_user_password());
        if(!md5_password.equals(user.getCn_user_password())){
            result.setStatus(2);
            result.setMsg("密码错误");
            return result;
        }
        //用户名密码正确
        result.setStatus(0);
        result.setMsg("用户名密码正确");
        result.setData(user.getCn_user_id()); //返回userId
        return result;
    }


}
