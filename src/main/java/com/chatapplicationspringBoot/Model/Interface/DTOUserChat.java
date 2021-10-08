package com.chatapplicationspringBoot.Model.Interface;

import lombok.Data;

@Data
public class DTOUserChat {
    private Long id;
    private String cnic;
    private String createdDate;
    private String email;
    private String password;
    private String updatedDate;
    private String userName;

}
