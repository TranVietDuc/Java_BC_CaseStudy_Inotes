package com.codegym.inotes.service.impl;

import com.codegym.inotes.model.Type;
import com.codegym.inotes.repository.TypeRepository;
import com.codegym.inotes.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type findById(int id) {
        return typeRepository.findById(id).get();
    }

    @Override
    public void save(Type type) {
         typeRepository.save(type);
    }

    @Override
    public void remove(int id) {
          typeRepository.deleteById(id);
    }
}
