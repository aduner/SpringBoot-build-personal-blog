package com.aduner.service;

import com.aduner.dao.TypeRepository;
import com.aduner.po.PoBlog;
import com.aduner.po.PoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    @Transactional
    public PoType saveType(PoType type) {
        return typeRepository.save(type);
    }

    @Override
    @Transactional
    public PoType getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    @Transactional
    public Page<PoType> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<PoType> listType() {
        return typeRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public PoType getTagByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<PoType> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        List<PoType> types=typeRepository.findTop(pageable);
        List<PoType> result=new ArrayList<PoType>();
        // 排除非发布blog和博客数量为0的type
        for(PoType type:types) {
            List<PoBlog> needBlogs = new ArrayList<PoBlog>();
            for (PoBlog blog : type.getBlogs()) {
                if (blog.isPublished()) {
                    needBlogs.add(blog);
                }
            }
            type.setBlogs(needBlogs);
            if(type.getBlogs().size()>0){
                result.add(type);
            }
        }
        return result;
    }
}
