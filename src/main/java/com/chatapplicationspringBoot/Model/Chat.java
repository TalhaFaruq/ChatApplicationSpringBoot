package com.chatapplicationspringBoot.Model;

import javax.persistence.*;

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

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @JsonIgnoreProperties("chatList")
//    private User user;
//
//    @JsonBackReference
//    public User getUser() {
//        return user;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long id) {
        this.chatId = id;
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
