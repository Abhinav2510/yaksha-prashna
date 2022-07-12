package org.yaksha.prashna.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "TAG")
@Data
public class Tag {
    @Id
    @GeneratedValue
    private long tagId;
    @Column(nullable = false)
    private String tagName;
    @Column
    private String tagDescription;

    @ManyToMany(mappedBy = "tags")
    private Set<Question> questions;
}
