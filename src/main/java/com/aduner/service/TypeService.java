package com.aduner.service;

import com.aduner.po.PoType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    PoType saveType(PoType type);

    PoType getType(Long id);

    Page<PoType> listType(Pageable pageable);

    List<PoType> listType();

    void deleteType(Long id);

    PoType getTagByName(String name);

    List<PoType> listTypeTop(Integer size);
}
