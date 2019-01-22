package com.codegym.inotes.service;

import com.codegym.inotes.model.Note;
import com.codegym.inotes.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NoteService {

    Page<Note> findAllByTypeAndTitleContaining(Type type,String title,Pageable pageable);

    Page<Note> findAllByTitleContaining(String title,Pageable pageable);

    Page<Note> findAll(Pageable pageable);

    Note findById(int id);

    void save(Note note);

    void remove(int id);

}
