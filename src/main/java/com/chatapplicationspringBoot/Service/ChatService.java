package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Repository.ChatRepository;
import com.chatapplicationspringBoot.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ChatService {

    private static final Logger logger = LogManager.getLogger(ChatService.class.getName());

    private ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Autowired
    private UserRepository userRepository;
   // public ChatService(UserRepository userRepository) {this.userRepository = userRepository;}

    public List<Chat> Listallchatbyuserid(Long userID) {
        return chatRepository.findByUserId(userID);
        }

    public Chat createuserchat(Long userID, Chat chat) throws Exception {
        return userRepository.findById(userID).map(user -> {
            chat.setUser(user);
            return chatRepository.save(chat);
        }).orElseThrow(() -> new Exception("Not Found"));

    }


    //Get all chat from Database
    public List<Chat> Listallchat() {
        return chatRepository.findAll();
    }

    //Save chat into database by getting values from controller
    public void saveChat(Chat chat) {
        Date date = new Date();
        chat.setAnswerDate(date.toString());
        chat.setQuestionDate(date.toString());
        chatRepository.save(chat);
    }

    //Update chat into database by getting values from controller
    public void updateChat(Chat chat) {
        Date date = new Date();
        chat.setUpdatedQuestionDate(date.toString());
        chat.setUpdatedAnswerDate(date.toString());
        chatRepository.save(chat);
    }

    //Find by ID chat from database
    public Chat getChat(Long id) {
        return chatRepository.findById(id).get();
    }

    //Delete chat from db
    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }


}
