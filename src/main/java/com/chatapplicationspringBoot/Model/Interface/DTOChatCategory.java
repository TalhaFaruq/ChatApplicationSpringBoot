package com.chatapplicationspringBoot.Model.Interface;


import com.chatapplicationspringBoot.Model.Entity.Category;
import com.chatapplicationspringBoot.Model.Entity.Chat;
import lombok.Data;

import java.util.List;

@Data
public class DTOChatCategory {
    private List<Chat> chatList;
    private List<Category> categoryList;
}
