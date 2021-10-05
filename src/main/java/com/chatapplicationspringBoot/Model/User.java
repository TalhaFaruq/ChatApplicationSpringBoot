package com.chatapplicationspringBoot.Model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
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


    @ManyToMany(cascade = CascadeType.MERGE, targetEntity = Category.class)
    @JoinTable(name = "user_category",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();
}
