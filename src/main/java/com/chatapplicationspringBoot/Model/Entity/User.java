package com.chatapplicationspringBoot.Model.Entity;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Talha Farooq
 * @version 0.3
 * @description Pojo Class/ Entity Class for User
 * @createdTime 5 October 2021
 */
@Data
@Entity
@Table(name = "user")
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
    @Column(nullable = false)
    private String password; //User Password
    @NotNull
    private boolean Status;
    private String createdDate;
    private String updatedDate;

    /**
     * For one to many relationship with Chat  i.e. one user can create many chats (Unidirectional)
     */
    @OneToMany(targetEntity = Chat.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private List<Chat> chat;


    @ManyToMany(cascade = CascadeType.MERGE, targetEntity = Category.class)
    @JoinTable(name = "user_category",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.MERGE, targetEntity = Role.class)
    private List<Role> roles = new ArrayList<>();



}
