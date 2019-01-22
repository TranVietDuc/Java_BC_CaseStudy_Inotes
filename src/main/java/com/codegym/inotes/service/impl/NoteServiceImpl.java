package com.codegym.inotes.service.impl;

import com.codegym.inotes.model.Note;
import com.codegym.inotes.model.Type;
import com.codegym.inotes.repository.NoteRepository;
import com.codegym.inotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Page<Note> findAllByTypeAndTitleContaining(Type type, String title, Pageable pageable) {
        return noteRepository.findAllByTypeAndTitleContaining(type,title,pageable);
    }

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public Page<Note> findAllByTitleContaining(String title, Pageable pageable) {
        return noteRepository.findAllByTitleContaining(title, pageable);
    }

    @Override
    public Note findById(int id) {
        return noteRepository.findById(id).get();
    }

    @Override
    public void save(Note note) {
       noteRepository.save(note);
    }

    @Override
    public void remove(int id) {
       noteRepository.deleteById(id);
    }
}
