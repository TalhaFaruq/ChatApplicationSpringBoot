package com.chatapplicationspringBoot.Model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * User
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
}
