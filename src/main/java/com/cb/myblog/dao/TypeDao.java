package com.cb.myblog.dao;

import com.cb.myblog.pojo.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDao {

    Long saveType(@Param("type") Type type);

    Type getType(Long id) ;

    List<Type> listType();

    int updateType(@Param("type") Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);

}
