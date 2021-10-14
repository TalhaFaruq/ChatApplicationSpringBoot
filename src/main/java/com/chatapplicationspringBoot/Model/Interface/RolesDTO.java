package com.chatapplicationspringBoot.Model.Interface;

import lombok.Data;

import java.util.List;

@Data
public class RolesDTO {
    private Long id;
    private String name;
    private String createdDate;
    private boolean status;
    private String updatedDate;


    List<PermissionsDTO> permissions;
}
