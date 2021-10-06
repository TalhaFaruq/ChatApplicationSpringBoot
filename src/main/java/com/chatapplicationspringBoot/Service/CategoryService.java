package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Talha Farooq
 * @version 0.3
 * @description This class implements logic of API. The Controller send data to their respective service class.
 * This class is Category Service class which has following functions/API's show all categories, get category by certain
 * ID, update category and delete category by certain ID. Logger is also used to keep tracks of logs whenever any api is called
 * the logs will be saved in file.
 * @creationDate 05 Octuber 2021
 */
@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Object Created for Logger Class
     */
    private static final Logger logger = LogManager.getLogger(CategoryService.class);


    /**
     * @return ResponseEntity which category list. and in else it just return not found status
     * @author Talha Farooq
     * @version 0.3
     * @desription This fuction get and show all the Categories which are saved in database. The data from database
     * comes in list so categorylist.
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity<List<Category>> ListAllCategory() {
        try {
            List<Category> categoryList = categoryRepository.findAll();
            logger.info("Checking Category List", categoryList);
            return ResponseEntity.ok().body(categoryList);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * @return only responseEntity Status
     * @author Talha Farooq
     * @version 0.3
     * @description Save Category into database by getting values from controller
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity saveCategory(Category category) {
        try {
            if (category.getName()!= null) {
                categoryRepository.save(category);
                logger.info("Saving Category");
                return new ResponseEntity(HttpStatus.OK);
            } else return new ResponseEntity("Do not give null values", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Unable to save Category in database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return ResponseEntity with one object of category
     * @author Talha Farooq
     * @version 0.3
     * @description Find by ID category from database
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity<Category> getCategory(Long id) {
        try {
            Category categoryResponseEntity = categoryRepository.findById(id).get();
            logger.info("Finding Category from certain ID");
            return ResponseEntity.ok().body(categoryResponseEntity);
        } catch (Exception e) {
            return new ResponseEntity("Unable to get certain category from database", HttpStatus.NOT_FOUND);
        }
    }


    /**
     * @return ResponseEntity
     * @author Talha Farooq
     * @description Delete category from db
     * @creationDate 05 Octuber 2021
     */
    public ResponseEntity deleteChat(Long id) {
        try {
            categoryRepository.deleteById(id);
            logger.info("Deletiog Category");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Unable to delete Category in database", HttpStatus.NOT_FOUND);
        }
    }
}
