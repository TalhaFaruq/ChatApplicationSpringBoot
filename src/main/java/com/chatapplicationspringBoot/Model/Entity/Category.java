package com.chatapplicationspringBoot.Model.Entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Talha Farooq
 * @version 0.3
 * @description Pojo Class/ Entity Class for Category
 * @createdTime 5 October 2021
 */
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
    @NotNull
    private boolean status;


}
