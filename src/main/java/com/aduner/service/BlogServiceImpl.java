package com.aduner.service;

import com.aduner.dao.BlogRepository;
import com.aduner.exception.NotFoundException;
import com.aduner.po.PoBlog;
import com.aduner.po.PoType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

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
    public PoBlog saveBlog(PoBlog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public PoBlog updateBlog(Long id, PoBlog blog) {
        PoBlog b = blogRepository.getOne(id);
        if (b == null)
            throw new NotFoundException("该博文不存在");
        BeanUtils.copyProperties(blog, b,MyBeanUtils.getNullPropertyNames(blog));
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
class MyBeanUtils {


    /**
     * 获取所有的属性值为空属性名数组
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds =  beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }

}
