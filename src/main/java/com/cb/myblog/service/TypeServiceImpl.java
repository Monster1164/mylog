package com.cb.myblog.service;

import com.cb.myblog.dao.TypeDao;
import com.cb.myblog.myException.NotFoundException;
import com.cb.myblog.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeDao typeDao =null;

    @Transactional
    @Override
    public Type saveType(Type type) {
        typeDao.saveType(type);
        return type;
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        Type type = typeDao.getType(id);
        return type;
    }

    @Transactional
    @Override
    public List<Type> listType() {
        List<Type> types = typeDao.listType();
        return types;
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeDao.getType(id);
        type.setId(id);
        if (t==null){
            throw new NotFoundException("不存在该类型");
        }

        typeDao.updateType(type);

        return type;
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        Type type = typeDao.getTypeByName(name);
        return type;
    }
}
