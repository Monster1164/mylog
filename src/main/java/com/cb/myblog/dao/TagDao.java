package com.cb.myblog.dao;

import com.cb.myblog.pojo.Tag;
import com.cb.myblog.pojo.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao {

    Long saveType(@Param("type") Tag type);

    Tag getType(Long id) ;

    List<Tag> listType();

    int updateType(@Param("type") Tag type);

    void deleteType(Long id);

    Tag getTypeByName(String name);

}
