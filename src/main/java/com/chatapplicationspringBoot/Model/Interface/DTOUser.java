package com.chatapplicationspringBoot.Model.Interface;

import lombok.Data;

import java.util.List;

@Data
public class DTOUser {
    private Long id;
    private int age;
    private String cnic;
    private String createdDate;
    private String email;
    private String password;
    private String updatedDate;
    private String userName;

    private List<DTOCategory> categories;
    private List<DTOChat> chats;


}
