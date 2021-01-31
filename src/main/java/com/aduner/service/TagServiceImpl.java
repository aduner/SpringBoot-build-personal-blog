package com.aduner.service;

import com.aduner.dao.TagRepository;
import com.aduner.po.PoTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    @Transactional
    public PoTag saveTag(PoTag tag) {
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public PoTag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    @Transactional
    public Page<PoTag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<PoTag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<PoTag> listTag(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idArray = ids.split(",");
            for (int i=0; i < idArray.length;i++) {
                list.add(Long.valueOf(idArray[i]));
            }
        }
        return list;
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public PoTag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
