package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.ResourceNotFoundException.ResourceNotFoundException;
import io.swagger.annotations.*;
import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Service.ChatService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.NoSuchElementException;

@EnableSwagger2
@RestController
@RequestMapping("/chat")
@Api(value = "This is chat class")

public class ChatController {

    //Object Created for Logger Class
    private static final Logger logger = LogManager.getLogger(ChatController.class);

    ChatService chatService;
    public ChatController(ChatService chatService) {this.chatService = chatService;}


    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    public boolean authorization(String key1) {
        return key.equals(key1);
    }

    @GetMapping("/user/{userId}/chat")
    public ResponseEntity<Object> listcoursebyid(@PathVariable(value = "userId") Long userId) {
        List<Chat> chatList = chatService.Listallchatbyuserid(userId);
        return new ResponseEntity(chatList, HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/chat")
    public ResponseEntity<Object> createCourse(@PathVariable(value = "userId") Long userId, @RequestBody Chat chat) throws Exception {
        chatService.createuserchat(userId, chat);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    //This API shows all the chats
    @GetMapping("")
    public ResponseEntity<Object> list(@RequestHeader("Authorization") String key1) {
        if (authorization(key1) == true) {
            logger.info("checking logs");
            List<Chat> chatList = chatService.Listallchat();
            return new ResponseEntity(chatList, HttpStatus.OK);
        }
        else return new ResponseEntity("Not Authorized",HttpStatus.UNAUTHORIZED);
    }

    //This API only show certain object by taking on ID number
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@RequestHeader("Authorization") String key1, @PathVariable Long id) {
        if (authorization(key1) == true) {
            try {
                Chat chat = chatService.getChat(id);
                logger.info("Get Chat from db with certain ID");
                return new ResponseEntity(chat, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Exist",HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity("Not Authorized",HttpStatus.UNAUTHORIZED);
    }

    //This API takes question with ID in header
    @GetMapping("/question")
    public ResponseEntity<Object> getquestion(@RequestHeader("Authorization") String key1, @RequestParam("question") Long id) {
        if (authorization(key1) == true) {
            try {
                Chat chat = chatService.getChat(id);
                logger.info("Quesiton with ID",chat);
                return new ResponseEntity(chat, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Exist",HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity("Not Authorized",HttpStatus.UNAUTHORIZED);
    }

    //This API just add the chat
    @PostMapping("/add")
    public ResponseEntity add(@RequestHeader("Authorization") String key1, @RequestBody Chat chat) {
        if (authorization(key1) == true) {
            chatService.saveChat(chat);
            logger.info("Chat Add into db",chat);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity("Not Authorized",HttpStatus.UNAUTHORIZED);
    }

    //This API updates the chat by just giving certain ID all values should be update otherwise other fields will be NULL
    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestHeader("Authorization") String key1, @RequestBody Chat chat) {
        if (authorization(key1) == true) {
            try {
                chatService.updateChat(chat);
                logger.info("Updated",chat);
                return new ResponseEntity("The Update has been made",HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Exist",HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity("Not Authorized",HttpStatus.UNAUTHORIZED);

    }

    //This API delete certain chat
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@RequestHeader("Authorization") String key1, @PathVariable Long id) {
        if (authorization(key1) == true) {
            chatService.deleteChat(id);
            logger.debug(id);
            return new ResponseEntity("The given ID has been deleted",HttpStatus.OK);
        }
        else return new ResponseEntity("Not Authorized",HttpStatus.OK);
    }
}
