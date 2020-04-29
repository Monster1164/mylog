package com.cb.myblog.service;

import com.cb.myblog.pojo.Tag;
import com.cb.myblog.pojo.Type;

import java.util.List;


public interface TagService {

    Tag saveType(Tag type);

    Tag getType(Long id);

    List<Tag> listType();

    List<Tag> listTag(String ids);

    Tag updateType(Long id, Tag type);

    void deleteType(Long id);

    Tag getTypeByName(String name);


}
