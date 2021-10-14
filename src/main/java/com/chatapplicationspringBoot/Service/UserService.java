package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Entity.SMS;
import com.chatapplicationspringBoot.Model.Entity.User;
import com.chatapplicationspringBoot.Model.Interface.ChatCategoriesOtherDTO;
import com.chatapplicationspringBoot.Model.Interface.ChatCategoryDTO;
import com.chatapplicationspringBoot.Model.Interface.UserDTO;
import com.chatapplicationspringBoot.Repository.ChatRepository;
import com.chatapplicationspringBoot.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    private final MailService mailService;
    private final SmsService smsService;

    /**
     * Constructor
     */
    public UserService(UserRepository userRepository, ChatRepository chatRepository, MailService mailService, SmsService smsService) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.mailService = mailService;
        this.smsService = smsService;
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
    public ResponseEntity<Object> listAllUser() {
        try {
            List<User> userList = (List<User>) userRepository.findByStatus();
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
    public ResponseEntity<Object> saveUser(User user) {
        try {
            LocalDateTime date = LocalDateTime.now();
            user.setCreatedDate(date.toString());
            if (!user.getChat().isEmpty()) {
                int size = user.getChat().size();
                for (int i = 0; i < size; i++) {
                    user.getChat().get(i).setQuestionDate(date.toString());
                    user.getChat().get(i).setAnswerDate((date.toString()));
                }
            }

            userRepository.save(user);
            logger.info("Saved user");
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.getCause());
         return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
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
            logger.info("Getting user by ID", useroObj.toString());
            if (useroObj.isPresent()) {
                return new ResponseEntity<>(useroObj, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no user with this ID", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity("User does not Exist in database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return ResponseEntity
     * @author Talha Farooq
     * Delete user from db
     * @since 05 October 2021
     */
    public ResponseEntity deleteUser(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                user.get().setDeleteStatus(true);
                logger.info("Deleting service class user");
                return new ResponseEntity("Deleted", HttpStatus.OK);
            }
            return new ResponseEntity("User does not Exist", HttpStatus.OK);
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


    /**
     * Gets chatand category list. If user is not exist then fawad getUser api is called and gets that user. Even if their
     * user is not present then fawad api will send notfound response
     *
     * @param userId the user id
     * @return the chatand category list
     * @since 14 October 2021
     */
    public ResponseEntity<Object> getChatandCategoryList(Long userId) {
        Optional<User> userObj = userRepository.findById(userId);
        if (userObj.isPresent()) {
            ChatCategoryDTO chatCategoryDTO = new ChatCategoryDTO();
            chatCategoryDTO.setCategoryList(userObj.get().getCategories());
            chatCategoryDTO.setChatList(userObj.get().getChat());
            return ResponseEntity.ok().body(chatCategoryDTO);
        } else {
            ResponseEntity<UserDTO> dtoUser = getfawaduser(userId);
            ChatCategoriesOtherDTO dtoChatCatogariesOther = new ChatCategoriesOtherDTO();
            dtoChatCatogariesOther.setChatDTOS(dtoUser.getBody().getChats());
            dtoChatCatogariesOther.setDtoCategories(dtoUser.getBody().getCategories());
            return new ResponseEntity<>(dtoChatCatogariesOther, HttpStatus.FOUND);
        }
    }

    public ResponseEntity<UserDTO> getfawaduser(Long userId) {

        httpHeaders.add("Authorization", "user12345");
        HttpEntity<UserDTO> httpEntity = new HttpEntity(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String UserResourceUrl = "http://192.168.10.15:8080/user/get/" + userId;
        ResponseEntity<UserDTO> dtoUser = restTemplate.exchange(UserResourceUrl, HttpMethod.GET, httpEntity, UserDTO.class);
        return dtoUser;
    }


    /**
     * Friendrequest response entity. If user is not exist then fawad getUser api is called and gets that user. Even if their
     * user is not present then fawad api will send notfound response
     *
     * @param userId the user id
     * @return the response entity
     */
    public ResponseEntity<Object> friendrequest(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            String phoneNumber = user.get().getPhoneNumber();
            String name = user.get().getFirstName();
            SMS sms = new SMS();
            sms.setTo(phoneNumber);
            sms.setMessage("Friend Request from " + name);
            smsService.send(sms);
            return new ResponseEntity<>("Friend Request send", HttpStatus.OK);
        } else {
            ResponseEntity<UserDTO> dtoUser = getfawaduser(userId);
            String phone = dtoUser.getBody().getPhoneNumber();
            String username = dtoUser.getBody().getUserName();
            SMS sms = new SMS();
            sms.setTo(phone);
            sms.setMessage("Friend Request from" + username);
            smsService.send(sms);
            return new ResponseEntity<>("Friend Request send", HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> tokensendemailandsms(Long userId) throws MessagingException {
        Optional<User> user = userRepository.findById(userId);
        try {
            if (user.isPresent()) {
                String email = user.get().getEmail();
                String phoneNumber = user.get().getPhoneNumber() ;
                String subject = "Verification";
                Random rnd = new Random(); //Generating a random number
                int token = rnd.nextInt(999999); //Generating a random number of 6 digits
                String message = "Please verify yourself \n Email token: "+ token;

                String name = user.get().getFirstName();
                SMS sms = new SMS();
                sms.setTo(phoneNumber);
                sms.setMessage(name +" please verify yourself " + token);

                smsService.send(sms);
                mailService.sendMail(email,subject,message);
                user.get().setToken(token);

                return new ResponseEntity<>("Mail and message Sent",HttpStatus.OK);
            }
            else return new ResponseEntity<>("User is not found",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> verificationsmsandemail(int smstoken, int emailtoken, String email){
        try {
            if(emailtoken == smstoken){
                User user = userRepository.findByUserIdAndEmailAndToken(email,emailtoken);
                user.setAccountStatus(true);
                userRepository.save(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            else return new ResponseEntity<>("Invalid Token",HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.UNAUTHORIZED);
        }

    }

}
