package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Entity.Chat;
import com.chatapplicationspringBoot.Service.ChatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Talha Farooq
 * @version 0.3
 * @description This class is Controller class which just takes the data from frontend and give data to frontend. Authorization is check in this class. Also
 * This class contains servlets path. It contains api list all chats, update certain chat, find chat and delete chat.
 * @createdTime 5 October 2021
 */
@EnableSwagger2
@RestController
@RequestMapping("/chat")
public class ChatController {

    /**
     * @author Talha Farooq
     * @version 0.3
     * @description This just create logger object to use in further program
     * @createdTime 5 October 2021
     */
    private static final Logger logger = LogManager.getLogger(ChatController.class);

    ChatService chatService;
    public ChatController(ChatService chatService) {this.chatService = chatService;}

    /**
     * This is token for checking authorization
     */
    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * Function for Authorization class
     */
    public boolean authorization(String token) {
        return key.equals(token);
    }

    private final String na="Not Authorize";

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the chats from database in ArrayList and shows it in frontend. With Authorization token.
     * @createdTime 5 October 2021
     */
    @GetMapping("/all")
    public ResponseEntity<List<Chat>> listAllChat(@RequestHeader("Authorization") String token) {
        if (authorization(token)) {
            logger.info("checking logs");
            return chatService.Listallchat();
        }
        else return new ResponseEntity(na,HttpStatus.UNAUTHORIZED);
    }

    /**
     * @return ResponseEntity with object
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the chat from database in object by certain ID and shows it in frontend. With Authorization token.
     * @createdTime 5 October 2021
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getByChatId(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {
            try {
                ResponseEntity<Chat> chat = chatService.getChat(id);
                logger.info("Get Chat from db with certain ID");
                return new ResponseEntity(chat, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Exist",HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity(na,HttpStatus.UNAUTHORIZED);
    }

    /**
     * @return ResponseEntity with an object whose question ID is match with given id
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the chat from database in object by question id matching and shows it in frontend. With Authorization token.
     * @createdTime 5 October 2021
     */
    @GetMapping("/question")
    public ResponseEntity<Object> getByQuestion(@RequestHeader("Authorization") String token, @RequestParam("question") Long id) {
        if (authorization(token)) {
            try {
                ResponseEntity<Chat> chat = chatService.getChat(id);
                logger.info("Quesiton with ID",chat);
                return new ResponseEntity(chat, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Exist",HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity(na,HttpStatus.UNAUTHORIZED);
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the data from frontend in json. With Authorization token. At same time more than one object can be saved.
     * @createdTime 5 October 2021
     */
    @PostMapping("/add")
    public ResponseEntity addChats(@RequestHeader("Authorization") String token, @RequestBody List<Chat> chatList) {
        if (authorization(token)) {
            for (Chat chats : chatList) {
                chatService.saveChat(chats);
            }
            logger.info("Chat Added into db",chatList);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity(na,HttpStatus.UNAUTHORIZED);
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API updates the chat in database. With Authorization token.
     * @createdTime 5 October 2021
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateChat(@RequestHeader("Authorization") String token, @RequestBody Chat chat) {
        if (authorization(token)) {
            try {
                chatService.updateChat(chat);
                logger.info("Updated",chat);
                return new ResponseEntity("The Update has been made",HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Exist",HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity(na,HttpStatus.UNAUTHORIZED);

    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API delete certain chat in the database
     * @createdTime 5 October 2021
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteChat(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {
            chatService.deleteChat(id);
            logger.debug(id);
            return new ResponseEntity("The given ID has been deleted",HttpStatus.OK);
        }
        else return new ResponseEntity(na,HttpStatus.OK);
    }
}
