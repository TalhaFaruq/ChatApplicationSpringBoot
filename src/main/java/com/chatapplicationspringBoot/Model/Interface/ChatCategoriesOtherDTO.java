package com.chatapplicationspringBoot.Model.Interface;

import lombok.Data;

import java.util.List;

@Data
public class ChatCategoriesOtherDTO {
    private List<ChatDTO> chatDTOS;
    private List<CategoryDTO> dtoCategories;
}
