package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Service.UserService;
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
    public Boolean authorization(String key1) {
        return key.equals(key1);
    }

    /**
     * Login it takes email and password from frontend then check from database by calling object with email
     */
    @GetMapping("/login")
    public ResponseEntity login(@RequestParam("email") String email, @RequestParam("password") String password) {
        int check = userService.findByEmail(email, password);
        switch (check) {
            case 1:
                logger.info("Logged in", email);
                return new ResponseEntity("You are now Logged in", HttpStatus.OK);
            case 2:
                logger.info("Password Wrong", email);
                return new ResponseEntity("Password Mismatch", HttpStatus.OK);
            default:
                return new ResponseEntity("Account doesn't exist", HttpStatus.OK);
        }
    }

    /**
     * This API shows all the users
     */
    @GetMapping("")
    public ResponseEntity<Object> list(@RequestHeader("Authorization") String key1) {
        if (authorization(key1)) {
            List<User> userList = userService.listAllUser();
            logger.info("Checking List",userList);
            return new ResponseEntity(userList, HttpStatus.OK);
        } else return new ResponseEntity(na, HttpStatus.UNAUTHORIZED);
    }

    /**
     * This API only show certain object by taking on ID number
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@RequestHeader("Authorization") String key1, @PathVariable Long id) {
        if (authorization(key1)) {
            try {
                User user = userService.getUser(id);
                logger.info("Get User", user);
                return new ResponseEntity(user, HttpStatus.OK);

            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Exist", HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity(na, HttpStatus.UNAUTHORIZED);
    }

    /**
     * This API just add the user
     */
    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestHeader("Authorization") String key1, @RequestBody User user) {
        if (authorization(key1)) {
            userService.saveUser(user);
            logger.info("New User Added",user);
            return new ResponseEntity("Added in database", HttpStatus.OK);
        } else return new ResponseEntity(na, HttpStatus.OK);
    }

    /**
     * This API updates the user by just giving certain ID all values should be update otherwise other fields will be NULL
     */
    @PutMapping("/update/")
    public ResponseEntity<Object> update(@RequestHeader("Authorization") String key1, @RequestBody User user) {
        if (authorization(key1)) {
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
     * This API updates checks auto by searching that id into database
     */
    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestHeader("Authorization") String key1, @RequestBody User user, @PathVariable long id) {
        if (authorization(key1)) {
            try {
                //User userObj = userService.getUser(id);
                user.setUserId(id);
                userService.updateUser(user);
                logger.info("User Updated",user);
                return new ResponseEntity("The update has been done", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity(na, HttpStatus.UNAUTHORIZED);
    }

    /**
     * This API deletes the user
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@RequestHeader("Authorization") String key1, @PathVariable Long id) {
        if (authorization(key1)) {
            userService.deleteUser(id);
            logger.info("update");
            return new ResponseEntity("The given ID has been deleted", HttpStatus.OK);
        } else return new ResponseEntity(na, HttpStatus.OK);
    }

    @PutMapping("/update/chat/{id}/")
    public ResponseEntity<Object> update(@RequestHeader("Authorization") String key1, @PathVariable (value = "id") Long chatId, @RequestParam (value = "userId") Long userId) {
        if (authorization(key1)) {
            try {
                String chat = userService.updateChat(userId,chatId);
                return new ResponseEntity(chat, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("The ID given is not in database", HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity(na, HttpStatus.UNAUTHORIZED);

    }
}
