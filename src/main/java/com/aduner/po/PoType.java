package com.aduner.po;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "t_type")
public class PoType {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "type")
    private List<PoBlog> blogs =new ArrayList<>();

    public Long getId() {
        return id;
    }
}
