package com.aduner.service;

import com.aduner.dao.TypeRepository;
import com.aduner.exception.NotFoundException;
import com.aduner.po.PoType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
