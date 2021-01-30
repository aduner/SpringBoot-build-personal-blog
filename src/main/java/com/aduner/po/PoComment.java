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
    private String userName;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @ManyToOne
    private PoBlog blog;
    @ManyToOne
    private PoComment replyComment;
    @OneToMany(mappedBy = "replyComment")
    private List<PoComment> parentComment=new ArrayList<>( );

}
