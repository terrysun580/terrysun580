package org.cloudnote.service;

import org.cloudnote.uitl.NoteResult;

public interface BookService {

    public NoteResult addBook(String userId,String bookName);

    public NoteResult loadUserBooks(String userId);
}
