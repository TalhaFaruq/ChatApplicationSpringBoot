package com.chatapplicationspringBoot.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @Column(nullable = false)
    private boolean Status;
    private String createdDate;
    private String updatedDate;

    @ManyToMany(cascade = CascadeType.MERGE, targetEntity = Privilege.class)
    private List<Privilege> privileges = new ArrayList<>();

}
