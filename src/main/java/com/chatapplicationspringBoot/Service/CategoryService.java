package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


      //Get all Category from Database
    public List<Category> ListAllCategory() {
        return categoryRepository.findAll();
    }


     // Save Category into database by getting values from controller
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }


    //  Update category into database by getting values from controller
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }


     // Find by ID category from database
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).get();
    }


     // Delete category from db
    public void deleteChat(Long id) {
        categoryRepository.deleteById(id);
    }
}
