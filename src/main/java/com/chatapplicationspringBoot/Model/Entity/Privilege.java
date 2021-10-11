package com.chatapplicationspringBoot.Model.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "privilege")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

}
