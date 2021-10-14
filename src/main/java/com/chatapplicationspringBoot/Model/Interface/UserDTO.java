package com.chatapplicationspringBoot.Model.Interface;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private int age;
    private String cnic;
    private String createdDate;
    private String email;
    private String password;
    private String phoneNumber;
    private String updatedDate;
    private boolean status;
    private String userName;

    private List<CategoryDTO> categories;
    private List<ChatDTO> chats;
    private List<RolesDTO> roles;


}
