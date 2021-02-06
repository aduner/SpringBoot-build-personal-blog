package com.aduner.po;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_comment")
public class PoComment {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @ManyToOne
    private PoBlog blog;
    @ManyToOne
    private PoComment replyComment;
    @OneToMany(mappedBy = "parentComment")
    private List<PoComment> replyComments = new ArrayList<>();
    @OneToMany(mappedBy = "replyComment")
    private List<PoComment> parentComments=new ArrayList<>( );
    @ManyToOne
    private PoComment parentComment;
    private boolean adminComment;


}
