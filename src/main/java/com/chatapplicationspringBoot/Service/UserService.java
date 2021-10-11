package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Entity.User;
import com.chatapplicationspringBoot.Model.Interface.DTOChatCategoriesOther;
import com.chatapplicationspringBoot.Model.Interface.DTOChatCategory;
import com.chatapplicationspringBoot.Model.Interface.DTOUser;
import com.chatapplicationspringBoot.Repository.ChatRepository;
import com.chatapplicationspringBoot.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Talha Farooq
 * @Description This class implements logic of API. The Controller send data to their respective service class.
 * This class is user Service class which has following functions/API's (major one is Login which authorize the user by
 * email and password), show all user, get user by certain ID, update user and delete user by
 * certain ID. Logger is also used to keep tracks of logs whenever any api is called the logs will be saved in file.
 * @creationDate 05 Octuber 2021
 */
@Service
public class UserService {

    HttpHeaders httpHeaders = new HttpHeaders();
    URI uri;

    /**
     * Not Autowired, Constructor is made
     */
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    /**
     * Constructor
     */
    public UserService(UserRepository userRepository, ChatRepository chatRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }

    /**
     * Object Created for Logger Class
     */
    private static final Logger logger = LogManager.getLogger(UserService.class);

    /**
     * @return ResponseEntity which return userlist. and in else it just return not found status
     * @author Talha Farooq
     * @version 0.3
     * @desription This fuction get and show all the user which are saved in database. The data from database
     * comes in list so userlist.
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity<List<User>> listAllUser() {
        try {
            List<User> userList = userRepository.findAll();
            if (!userList.isEmpty()) {
                logger.info("In Service class getting All list");
                return ResponseEntity.ok().body(userList);
            } else return new ResponseEntity("List Empty", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Cannot access List of User from database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return only responseEntity Status
     * @author Talha Farooq
     * @version 0.3
     * @description Save user into database by getting values from controller and set date/time of created question and
     * answer of chat
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity saveUser(User user) {
        try {
            LocalDateTime date = LocalDateTime.now();
            int size = user.getChat().size();
            for (int i = 0; i < size; i++) {
                user.getChat().get(i).setQuestionDate(date.toString());
                user.getChat().get(i).setAnswerDate((date.toString()));
            }
            userRepository.save(user);
            logger.info("Saved user");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Cannot save values in database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return only responseEntity Status
     * @author Talha Farooq
     * @version 0.3
     * @description update user into database by getting values from controller and set date/time of updated question and
     * answer of chat
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity updateUser(User user) {
        try {
            LocalDateTime date = LocalDateTime.now();
            int size = user.getChat().size();
            for (int i = 0; i < size; i++) {
                user.getChat().get(i).setUpdatedQuestionDate(date.toString());
                user.getChat().get(i).setUpdatedAnswerDate(date.toString());
            }
            userRepository.save(user);
            logger.info("Updated User");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Cannot update the user into database", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Update by user ID But chat greater then 1 is not showing
     */
    public String updateChat(Long chatid, Long userid) {
        logger.info("update chat through user");
        return chatRepository.findChatByUserIdAndChatId(userid, chatid);
    }

    /**
     * @return ResponseEntity with one object of user
     * @author Talha Farooq
     * @version 0.3
     * @description Find by ID user from database
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity<Object> getUser(Long id) {
        try {
            Optional<User> useroObj = userRepository.findById(id);
            logger.info("Getting user by ID",useroObj.toString());
            if(useroObj.isPresent()){
                return new ResponseEntity<>(useroObj,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("There is no user with this ID",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity("User does not Exist in database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return ResponseEntity
     * @author Talha Farooq
     * @description Delete user from db
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            logger.info("Deleted user by certain ID");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Cannot Access certain user id from database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Custom API logic for email ;)
     */
    public boolean findByEmailandPassword(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);
            if (user.getPassword().equals(password)) {
                logger.info("Checking Login email and password");
                return true;
            } else return false;
        } catch (Exception e) {
            System.out.print("User not exist");
            return false;
        }
    }


    public ResponseEntity<Object> getChatandCategoryList(Long userId){
        Optional<User> userObj = userRepository.findById(userId);
        if(userObj.isPresent()){
            DTOChatCategory dtoChatCategory = new DTOChatCategory();
            dtoChatCategory.setCategoryList(userObj.get().getCategories());
            dtoChatCategory.setChatList(userObj.get().getChat());
            return ResponseEntity.ok().body(dtoChatCategory);
        }
        else {
            httpHeaders.add("Authorization","user12345");
            HttpEntity<DTOUser> httpEntity = new HttpEntity(httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            String UserResourceUrl= "http://192.168.10.15:8080/user/get/"+userId;
           ResponseEntity<DTOUser> dtoUser= restTemplate.exchange(UserResourceUrl, HttpMethod.GET, httpEntity , DTOUser.class);
            DTOChatCategoriesOther dtoChatCatogariesOther= new DTOChatCategoriesOther();
            dtoChatCatogariesOther.setDtoChats(dtoUser.getBody().getChats());
            dtoChatCatogariesOther.setDtoCategories(dtoUser.getBody().getCategories());
            return new ResponseEntity<>(dtoChatCatogariesOther,HttpStatus.FOUND);
        }
    }

}
