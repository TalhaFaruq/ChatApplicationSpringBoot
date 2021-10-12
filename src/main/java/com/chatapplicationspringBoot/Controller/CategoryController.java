package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Entity.Category;
import com.chatapplicationspringBoot.Service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Talha Farooq
 * @version 0.3
 * @description This class is Controller class which just takes the data from frontend and give data to frontend. Authorization is check in this class. Also
 * This class contains servlets path
 * @createdTime 5 October 2021
 */
@RestController
public class CategoryController {

    /**
     * @author Talha Farooq
     * @version 0.3
     * @description This just create logger object to use in further program
     * @createdTime 5 October 2021
     */
    private static final Logger logger = LogManager.getLogger(ChatController.class);

    @Autowired
    private CategoryService categoryService;

    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * Function for Authorization class
     */
    public boolean authorization(String token) {
        return key.equals(token);
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the Category from frontend in json. With Authorization token. At same time more than one object can be saved.
     * @createdTime 5 October 2021
     */
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestHeader("Authorization") String token, @RequestBody List<Category> categoryList) {
        if (authorization(token)) {
            for (Category categories : categoryList) {
                categoryService.saveCategory(categories);
            }
            logger.info("Adding Category");
            return new ResponseEntity(HttpStatus.OK);
        } else return new ResponseEntity("Not Authorized", HttpStatus.UNAUTHORIZED);
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the Categories from database in ArrayList and shows it in frontend. With Authorization token.
     * @createdTime 5 October 2021
     */
    @GetMapping("/all")
    public ResponseEntity<List<Category>> listAllCategoris(@RequestHeader("Authorization") String token) {
        if (authorization(token)) {
            return categoryService.ListAllCategory();
        } else return new ResponseEntity("Not Authorized", HttpStatus.UNAUTHORIZED);
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API updates the Category in database. With Authorization token.
     * @createdTime 5 October 2021
     */
    @GetMapping("/update")
    public ResponseEntity<Object> updateCategory(@RequestHeader("Authorization") String token, @RequestBody Category category) {
        if (authorization(token)) {
            try {
                categoryService.saveCategory(category);
                logger.info("Updated", category);
                return new ResponseEntity("The Update has been made", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Not Exist", HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity("", HttpStatus.UNAUTHORIZED);

    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API delete certain category in the database
     * @createdTime 5 October 2021
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategorybyId(@RequestHeader("Authorization") String Token, @PathVariable Long id) {

        if (authorization(Token)) {
        logger.info("Delete in Category Controller");
        return categoryService.deleteChat(id);
        }
        else return new ResponseEntity("Does not Exist", HttpStatus.OK);
    }
}



