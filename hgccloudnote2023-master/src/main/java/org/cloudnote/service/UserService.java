package org.cloudnote.service;

import org.cloudnote.uitl.NoteResult;

public interface UserService {
    public NoteResult checkLogin(String name,String password);

    NoteResult regist(String name, String nick, String password);
}
