package com.Atharv.question.service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
    @SequenceGenerator(name = "question_seq", sequenceName = "question_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
    private Integer id;

    @Column(name = "questiontitle") // maps to existing DB column
    private String questionTitle;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String rightanswer;
    private String difficultylevel;
    private String category;
}
