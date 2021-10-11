package com.chatapplicationspringBoot.Model.Interface;

import lombok.Data;

import java.util.List;

@Data
public class DTOChatCategoriesOther {
    private List<DTOChat> dtoChats;
    private List<DTOCategory> dtoCategories;
}
