package com.aduner.service;

import com.aduner.dao.BlogRepository;
import com.aduner.dao.TagRepository;
import com.aduner.exception.NotFoundException;
import com.aduner.po.PoBlog;
import com.aduner.po.PoTag;
import com.aduner.po.PoType;
import com.aduner.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    TagRepository tagRepository;

    @Override
    public PoBlog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<PoBlog> listBlog(Pageable pageable, PoBlog blog) {
        return blogRepository.findAll(new Specification<PoBlog>() {
            @Override
            public Predicate toPredicate(Root<PoBlog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                if (blog.getType() != null && blog.getType().getId() != null) {
                    predicates.add(cb.equal(root.<PoType>get("type").get("id"), blog.getType().getId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<PoBlog> listPublishBlog(Pageable pageable, PoBlog blog) {
        return blogRepository.findAll(new Specification<PoBlog>() {
            @Override
            public Predicate toPredicate(Root<PoBlog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                if (blog.getType() != null && blog.getType().getId() != null) {
                    predicates.add(cb.equal(root.<PoType>get("type").get("id"), blog.getType().getId()));
                }
                predicates.add(cb.equal(root.<Boolean>get("published"),true));
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<PoBlog> listPublishBlog(Pageable pageable) {
        return blogRepository.findPublish(pageable);
    }

    @Override
    public Page<PoBlog> listTagBlog(Pageable pageable,Long id) {
        PoTag tag = tagRepository.findById(id).get();
        // 排除非发布blog
        List<PoBlog> needBlogs = new ArrayList<PoBlog>();
        for (PoBlog blog : tag.getBlogs()) {
            if (blog.isPublished()) {
                needBlogs.add(blog);
            }
        }
        tag.setBlogs(needBlogs);
        return new PageImpl<PoBlog>(needBlogs,pageable,needBlogs.size());
    }



    @Override
    public PoBlog saveBlog(PoBlog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public PoBlog updateBlog(Long id, PoBlog blog) {
        PoBlog b = blogRepository.getOne(id);
        if (b == null)
            throw new NotFoundException("该博文不存在");
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        return blogRepository.save(b);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<PoBlog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }
}
