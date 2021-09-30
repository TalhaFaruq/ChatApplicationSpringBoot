package com.chatapplicationspringBoot.Model;

import javax.persistence.*;

@Entity
public class Chat {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id; //Chat ID having a question and answer
    @Column(nullable = false, unique = true)
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }

    public String getUpdatedQuestionDate() {
        return updatedQuestionDate;
    }

    public void setUpdatedQuestionDate(String updatedQuestionDate) {
        this.updatedQuestionDate = updatedQuestionDate;
    }

    public String getUpdatedAnswerDate() {
        return updatedAnswerDate;
    }

    public void setUpdatedAnswerDate(String updatedAnswerDate) {
        this.updatedAnswerDate = updatedAnswerDate;
    }
}
