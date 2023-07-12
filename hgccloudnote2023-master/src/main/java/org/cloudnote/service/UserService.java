package org.cloudnote.service;

import org.cloudnote.uitl.NoteResult;

public interface UserService {
    public NoteResult checkLogin(String name,String password);

    public NoteResult changePassword(String userId,String oldPassword,String newPassword);

    NoteResult regist(String name, String nick, String password);



}
