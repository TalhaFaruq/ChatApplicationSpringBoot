package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Repository.ChatRepository;
import com.chatapplicationspringBoot.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {


    //Not Autowired, Constructor is made
    private final UserRepository userRepository;
    //Constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void Adduseradnlist(User user, List<Chat> chatList){
        int i=0;
        userRepository.save(user);
        ChatRepository chatRepository = null;
        while(chatList.iterator().hasNext()){
            chatRepository.save(chatList.get(i));
            i++;
        }
    }

    //Get all users from Database
    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    //Save User into database by getting values from controller
    public void saveUser(User user) {
        Date date = new Date();
        user.setDob(date.toString());
        userRepository.save(user);
    }

    //Update user into database by getting values from controller
    public void updateUser(User user) {
        userRepository.save(user);
    }

    /**
     * Find by ID User from database
     */
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }


    /**
     * Delete user from db
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Custom API logic for email ;)
     */
    public Integer findByEmail(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);
            if (user.getPassword().equals(password)) return 1;
            else return 2;
        } catch (Exception e) {
            return 3;
        }
    }
}
