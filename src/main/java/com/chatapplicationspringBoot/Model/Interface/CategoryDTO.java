package com.chatapplicationspringBoot.Model.Interface;

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String createdDate;
    private String updatedDate;
    private boolean status;

}
