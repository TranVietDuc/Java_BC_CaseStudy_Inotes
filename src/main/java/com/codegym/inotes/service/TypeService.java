package com.codegym.inotes.service;

import com.codegym.inotes.model.Type;

public interface TypeService {

        Iterable<Type> findAll();

        Type findById(int id);

        void save(Type province);

        void remove(int id);
}
