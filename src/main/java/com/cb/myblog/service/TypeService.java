package com.cb.myblog.service;

import com.cb.myblog.pojo.Type;

import java.util.List;


public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    List<Type> listType();

    Type updateType(Long id ,Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);
}
