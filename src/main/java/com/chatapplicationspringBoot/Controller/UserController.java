package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    public boolean authorization(String key1) {
        if (key.equals(key1)) return true;
        else return false;
    }

    @GetMapping("")
    public List<User> list() {
        return userService.listAllUser();

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@RequestHeader("Authorization") String key1, @PathVariable Long id) {

        try {
            if (authorization(key1) == true) {
                User user = userService.getUser(id);
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } else return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public void add(@RequestHeader("Authorization") String key1, @RequestBody User user) {
        if (authorization(key1) == true) {
            userService.saveUser(user);
        }

    }

    @PutMapping("/updatenew")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String key1, @RequestBody User user) {
        if (authorization(key1) == true) {
            try {
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String key1,@RequestBody User user, @PathVariable long id) {
        if (authorization(key1) == true) {
        try {
            User userObj = userService.getUser(id);
            user.setId(id);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }}
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader("Authorization") String key1,@PathVariable Long id) {
        if (authorization(key1) == true) {
            userService.deleteUser(id);
        }
    }
}
