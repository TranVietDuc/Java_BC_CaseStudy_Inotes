package com.codegym.inotes.repository;

import com.codegym.inotes.model.Note;
import com.codegym.inotes.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note,Integer> {
 Page<Note> findAllByTypeAndTitleContaining(Type type,String title,Pageable pageable);
 Page<Note> findAllByTitleContaining(String title,Pageable pageable);
}
