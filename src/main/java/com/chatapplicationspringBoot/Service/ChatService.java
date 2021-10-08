package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Entity.Chat;
import com.chatapplicationspringBoot.Repository.ChatRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Talha Farooq
 * @version 0.3
 * @Description This class implements logic of API. The Controller send data to their respective service class.
 * This class is Chat Service class which has show all chats, get chat by certain ID, update chat and delete chat
 * certain ID. Logger is also used to keep tracks of logs whenever any api is called the logs will be saved in
 * file.
 * @creationDate 05 Octuber 2021
 */
@Service
public class ChatService {

    public static final Logger logger = LogManager.getLogger(ChatService.class);

    private ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    /**
     * @return ResponseEntity which return chatlist. and in else it just return not found status
     * @author Talha Farooq
     * @version 0.3
     * @desription This fuction get and show all the chats which are saved in database. The data from database
     * comes in list so chatlist.
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity<List<Chat>> Listallchat() {
        try {
            List<Chat> chatList = chatRepository.findAll();
            logger.info("Showing all Chats", chatList);
            return ResponseEntity.ok().body(chatList);
        } catch (Exception e) {
            return new ResponseEntity("Cannot Show List of Chat", HttpStatus.NOT_FOUND);
        }
    }


    /**
     * @return only responseEntity Status
     * @author Talha Farooq
     * @version 0.3
     * @description Save chat into database by getting values from controller and set date/time of created question and
     * answer
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity saveChat(Chat chat) {
        try {
            LocalDateTime date = LocalDateTime.now();
            chat.setAnswerDate(date.toString());
            chat.setQuestionDate(date.toString());
            chatRepository.save(chat);
            logger.info("saving chat");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Cannot save chat in database", HttpStatus.OK);
        }

    }

    /**
     * @return only responseEntity Status
     * @author Talha Farooq
     * @version 0.3
     * @description update chat into database by getting values from controller and set date/time of updated question and
     * answer
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity updateChat(Chat chat) {
        try {
            LocalDateTime date = LocalDateTime.now();
            chat.setUpdatedQuestionDate(date.toString());
            chat.setUpdatedAnswerDate(date.toString());
            chatRepository.save(chat);
            logger.info("Updating chat");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Cannot Update chat in database", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * @return ResponseEntity with one object of chat
     * @author Talha Farooq
     * @version 0.3
     * @description Find by ID chat from database
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity<Chat> getChat(Long id) {
        try {
            Chat chatobj = chatRepository.findById(id).get();
            logger.info("Getting certain chat", chatobj);
            return ResponseEntity.ok().body(chatobj);
        } catch (Exception e) {
            return new ResponseEntity("Chat do not Exist", HttpStatus.NOT_FOUND);
        }
    }


    /**
     * @return ResponseEntity
     * @author Talha Farooq
     * @description Delete chat from db
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity deleteChat(Long id) {
        try {
            chatRepository.deleteById(id);
            logger.info("Deleteing Id");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Cannot Find chat to delete", HttpStatus.NOT_FOUND);
        }

    }


}
