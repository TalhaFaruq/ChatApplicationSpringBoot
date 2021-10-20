package com.chatapplicationspringBoot.Model.Interface;

import lombok.Data;

/**
 * The type Chat dto.
 */
@Data
public class ChatDTO {

    private Long id;
    private String question;
    private String answer;
    private String createdDate;
    private String updatedDate;
    private boolean status;

}
