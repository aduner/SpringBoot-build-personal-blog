package com.aduner.service;

import com.aduner.po.PoTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    PoTag saveTag(PoTag tag);

    PoTag getTag(Long id);

    Page<PoTag> listTag(Pageable pageable);

    List<PoTag> listTag();

    List<PoTag> listTag(String ids);

    void deleteTag(Long id);

    PoTag getTagByName(String name);

}
