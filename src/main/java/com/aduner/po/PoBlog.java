package com.aduner.po;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_blog")
public class PoBlog {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String flag;
    private Integer views;
    private Integer commentsCount;
    private boolean appreciation;
    private boolean copyright;
    private boolean comments;
    private boolean published;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @ManyToOne
    private PoType poType;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<PoTag> poTags=new ArrayList<>();
    @ManyToOne
    private PoUser poUser;
    @OneToMany(mappedBy = "poBlog")
    private List<PoComment> poComments=new ArrayList<>();
}
