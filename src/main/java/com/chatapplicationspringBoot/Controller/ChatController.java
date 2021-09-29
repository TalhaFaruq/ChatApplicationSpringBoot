package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Repository.ChatRepository;
import com.chatapplicationspringBoot.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService chatService;
    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    public boolean authorization(String key1) {
        if (key.equals(key1)) return true;
        else return false;
    }

    @GetMapping("")
    public List<Chat> list() {
        return chatService.Listallchat();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> get(@RequestHeader("Authorization") String key1,@PathVariable Long id) {
        if (authorization(key1) == true) {
            try {
                Chat chat = chatService.getChat(id);
                return new ResponseEntity<Chat>(chat, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<Chat>(HttpStatus.NOT_FOUND);
            }
        }else return new ResponseEntity<Chat>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/question")
    public ResponseEntity<Chat> getquestion(@RequestParam ("question") Long id){
        try {
            Chat chat = chatService.getChat(id);
            return new ResponseEntity<Chat>(chat, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Chat>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public void add(@RequestBody Chat chat) {
        chatService.saveChat(chat);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Chat chat) {
        try{
            chatService.saveChat(chat);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
