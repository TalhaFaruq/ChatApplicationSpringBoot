package com.chatapplicationspringBoot.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
//@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")

public class User {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long userId; //User ID
    @Column(nullable = false)
    private String firstName; //User First Name
    @Column(nullable = false)
    private String lastName;//User Last Name
    @Column(nullable = false,unique = true)
    private String email;//User email
    private int age;//User age
    @Column(nullable = true)
    private String dob;//User date of birth
    @Column(nullable = false)
    private String password; //User Password

    @OneToMany(targetEntity = Chat.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private List<Chat> chat;

    @ManyToMany(targetEntity = Category.class,cascade = CascadeType.ALL)
    @JoinTable(name="user_category", joinColumns=@JoinColumn(name="userId"),
            inverseJoinColumns=@JoinColumn(name="category_id"))
    private List<Category> categoryList =  new ArrayList<>();

    public User() {
    }

    public User(long uid, String firstName, String lastName, String email, int age, String dob, String password) {
        this.userId = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
        this.age= age;
        this.dob=dob;
        this.password=password;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        this.userId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chat> getChat() {
        return chat;
    }

    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
