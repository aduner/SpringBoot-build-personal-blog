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
    private boolean comment;
    private boolean published;
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @ManyToOne
    private PoType type;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<PoTag> tags =new ArrayList<>();
    @ManyToOne
    private PoUser user;
    @OneToMany(mappedBy = "blog")
    private List<PoComment> comments =new ArrayList<>();

}
