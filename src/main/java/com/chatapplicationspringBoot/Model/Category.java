package com.chatapplicationspringBoot.Model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;          //Auto generate ID
    @NotNull
    @Column(unique = true)
    private String name;


}
