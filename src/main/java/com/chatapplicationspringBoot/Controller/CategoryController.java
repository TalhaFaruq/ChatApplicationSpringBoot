package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CategoryController {

    /**
     * Object Created for Logger Class
     */
    private static final Logger logger = LogManager.getLogger(ChatController.class);

    @Autowired
    private CategoryService categoryService;


    /**
     * This is token for checking authorization
     */
    private String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * Function for key authorization
     */
    public boolean authorization(String key1) {
        return token.equals(key1);
    }


     /**
      * This API just add the chat
      */
    @PostMapping("/add")
    public ResponseEntity add(@RequestHeader("Authorization") String key1, @RequestBody Category category) {
        if (authorization(key1)) {
            categoryService.saveCategory(category);
            logger.info("Adding Category");
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity("Not Authorized",HttpStatus.UNAUTHORIZED);
    }

    /**
     * Show all Categories
     */
    @GetMapping("/all")
    public ResponseEntity<List<Category>> list(@RequestHeader("Authorization") String key1) {
        if (authorization(key1)) {
            return categoryService.ListAllCategory();
        }
        else return new ResponseEntity("Not Authorized",HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<Object> update(@RequestHeader("Authorization") String key1, @RequestBody Category category) {
        if (authorization(key1)) {
            try {
                categoryService.saveCategory(category);
                logger.info("Updated",category);
                return new ResponseEntity("The Update has been made",HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Exist",HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity("",HttpStatus.UNAUTHORIZED);

    }


}
