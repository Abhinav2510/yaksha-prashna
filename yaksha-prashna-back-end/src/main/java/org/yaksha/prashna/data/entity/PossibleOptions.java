package org.yaksha.prashna.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "POSSIBLE_OPTIONS")
@Data
public class PossibleOptions {
    @Id
    @GeneratedValue
    private long possibleOptionId;

    @Column(nullable = false)
    private String optionText;

    @ManyToOne
    private Question question;
}
