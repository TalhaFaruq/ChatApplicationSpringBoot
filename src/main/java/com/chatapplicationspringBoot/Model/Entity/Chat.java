package com.chatapplicationspringBoot.Model.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Talha Farooq
 * @version 0.3
 * @description Pojo Class/ Entity Class for Chat
 * @createdTime 5 October 2021
 */
@Data
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long chatId; //Chat ID having a question and answer
    @Column(nullable = false)
    private String question; //Chat question
    @Column(nullable = false)
    private String answer; //Chat answer
    @Column(nullable = true)
    private String questionDate; //Chat question Date
    @Column(nullable = true)
    private String answerDate;  //Chat answer Date
    @Column(nullable = true)
    private String updatedQuestionDate;     //Chat question Updated Date
    @Column(nullable = true)
    private String updatedAnswerDate;  //Chat answer Updated Date

}
