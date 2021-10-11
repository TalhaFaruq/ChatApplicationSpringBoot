package com.chatapplicationspringBoot.Model.Interface;

import lombok.Data;

@Data
public class DTOChat {

    private Long id;
    private String question;
    private String answer;
    private String createdDate;
    private String updatedDate;

}
