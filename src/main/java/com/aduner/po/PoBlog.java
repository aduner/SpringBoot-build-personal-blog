package com.aduner.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_blog")
@EntityListeners(AuditingEntityListener.class)
public class PoBlog {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Basic(fetch = FetchType.LAZY) //懒加载
    @Lob //大文本
    private String content;
    private String description;
    private String flag;
    private Integer views=0;
    private Integer commentsCount=0;
    private boolean appreciation;
    private boolean copyright;
    private boolean comment;
    private boolean published;
    private boolean recommend;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date creationTime;

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private Date updateTime;
    @ManyToOne
    private PoType type;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<PoTag> tags =new ArrayList<>();
    @ManyToOne
    private PoUser user;
    @OneToMany(mappedBy = "blog")
    private List<PoComment> comments =new ArrayList<>();
    @Transient
    private String tagIds;

}
