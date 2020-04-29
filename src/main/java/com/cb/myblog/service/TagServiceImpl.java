package com.cb.myblog.service;

import com.cb.myblog.dao.TagDao;
import com.cb.myblog.myException.NotFoundException;
import com.cb.myblog.pojo.Tag;
import com.cb.myblog.util.TagidsStringToList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao typeDao =null;

    @Transactional
    @Override
    public Tag saveType(Tag type) {
        Long id = typeDao.saveType(type);
        type.setId(id);

        return type;
    }

    @Transactional
    @Override
    public Tag getType(Long id) {
        Tag type = typeDao.getType(id);
        return type;
    }

    @Transactional
    @Override
    public List<Tag> listType() {
        List<Tag> types = typeDao.listType();
        return types;
    }

    @Override
    public List<Tag> listTag(String ids) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = TagidsStringToList.convertToList(ids);
        for (Long long1 : longs) {
            tags.add(typeDao.getType(long1));
        }
        return tags;
    }
//    private List<Long> convertToList(String ids) {  //把前端的tagIds字符串转换为list集合
//        List<Long> list = new ArrayList<>();
//        if (!"".equals(ids) && ids != null) {
//            String[] idarray = ids.split(",");
//            for (int i=0; i < idarray.length;i++) {
//                list.add(new Long(idarray[i]));
//            }
//        }
//        return list;
//    }


    @Transactional
    @Override
    public Tag updateType(Long id, Tag type) {
        Tag t = typeDao.getType(id);
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
    public Tag getTypeByName(String name) {
        Tag type = typeDao.getTypeByName(name);
        return type;
    }
}
