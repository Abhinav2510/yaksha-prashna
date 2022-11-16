package org.yaksha.prashna.data.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;
@Entity(name = "QUESTIONS")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionId;
    private String questionText;

    @OneToOne
    private PossibleOptions correctOption;

    @OneToMany(mappedBy = "question")
    private Set<PossibleOptions> options;

    @ManyToMany
    @JoinTable(
            name = "QUESTIONS_TAG",
            joinColumns = @JoinColumn(name = "questionId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    private Set<Tag> tags;
}
