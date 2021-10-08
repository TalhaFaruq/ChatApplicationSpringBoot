package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Entity.User;
import com.chatapplicationspringBoot.Service.UserService;
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
 * This class contains servlets path. It contains api list all users, update certain user, find user and delete user. This class also contains
 * onetomany relationship with chat class and manytomany relationship with category class. All relationship are unidirectional.
 * @createdTime 5 October 2021
 */
@EnableSwagger2
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(ChatController.class);

    final UserService userService;
    private final String na="Not Authorize";

    /**
     * UserService constructor, used in place of Autowired
     */
    public UserController(UserService userService) { this.userService = userService; }

    /**
     * This is token for checking authorization
     */
    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";


    /**
     * Authorization function
     */
    public Boolean authorization(String token) {
        return key.equals(token);
    }

    /**
     * Login it takes email and password from frontend then check from database by calling object with email
     */
    @GetMapping("/login")
    public ResponseEntity login(@RequestParam("email") String email, @RequestParam("password") String password) {
         if(userService.findByEmailandPassword(email, password)){
             return new ResponseEntity("Logged in", HttpStatus.OK);
         }
         else return new ResponseEntity("Email not Exist", HttpStatus.OK);
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the user from database in ArrayList and shows it in frontend. With Authorization token. With User
     * chat and category is also shown as that is because of relationship
     * @createdTime 5 October 2021
     */
    @GetMapping("all")
    public ResponseEntity<List<User>> listAllUsers(@RequestHeader("Authorization") String token) {
        if (authorization(token)) {
            logger.info("Checking List");
            return userService.listAllUser();
        } else return new ResponseEntity(na, HttpStatus.UNAUTHORIZED);
    }

    /**
     * @return ResponseEntity with object
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the user from database in object and shows it in frontend. With Authorization token. object in which chats and
     * cateegory will both be shown as they are associated with user
     * @createdTime 5 October 2021
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getByUserID(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {
            try {
                ResponseEntity<User> user = userService.getUser(id);
                logger.info("Get User", user);
                return new ResponseEntity(user, HttpStatus.OK);

            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Exist", HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity(na, HttpStatus.UNAUTHORIZED);
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the user from frontend in json. With Authorization token.
     * @createdTime 5 October 2021
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestHeader("Authorization") String token, @RequestBody User user) {
        if (authorization(token)) {
            userService.saveUser(user);
            logger.info("New User Added",user);
            return new ResponseEntity("Added in database", HttpStatus.OK);
        } else return new ResponseEntity(na, HttpStatus.OK);
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API update the user in database. With Authorization token.
     * @createdTime 5 October 2021
     */
    @PutMapping("/update/")
    public ResponseEntity<Object> updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) {
        if (authorization(token)) {
            try {
                userService.updateUser(user);
                logger.info("Updated",user);
                return new ResponseEntity("The update has been made in database", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("The ID given is not in database", HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity(na, HttpStatus.UNAUTHORIZED);

    }


    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API delete certain user in the database
     * @createdTime 5 October 2021
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {
            userService.deleteUser(id);
            logger.info("update");
            return new ResponseEntity("The given ID has been deleted", HttpStatus.OK);
        } else return new ResponseEntity(na, HttpStatus.OK);
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API update chat in database. With Authorization token.
     * @createdTime 5 October 2021
     */
    @GetMapping("/update/chat/{id}/")
    public ResponseEntity<Object> updateChatfromUser(@RequestHeader("Authorization") String token, @PathVariable (value = "id") Long chatId, @RequestParam (value = "userId") Long userId) {
        if (authorization(token)) {
            try {
                String chat = userService.updateChat(userId,chatId);
                return new ResponseEntity(chat, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("The ID given is not in database", HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity(na, HttpStatus.UNAUTHORIZED);

    }
}
