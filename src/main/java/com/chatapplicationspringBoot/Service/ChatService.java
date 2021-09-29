package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    public List<Chat> Listallchat(){
        return chatRepository.findAll();
    }

    public void saveChat(Chat chat) {
        Date date = new Date();
        chat.setAnswerDate(date.toString());
        chat.setQuestionDate(date.toString());
        chatRepository.save(chat);
    }

    public void updateChat(Chat chat){
        Date date = new Date();
        chat.setAnswerDate(date.toString());
        chat.setQuestionDate(date.toString());
        chatRepository.save(chat);
    }

    public Chat getChat(Long id) {
        return chatRepository.findById(id).get();
    }

    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }


}
