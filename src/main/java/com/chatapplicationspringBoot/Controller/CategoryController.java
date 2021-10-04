//package com.chatapplicationspringBoot.Controller;
//
//import com.chatapplicationspringBoot.Model.Category;
//import com.chatapplicationspringBoot.Service.CategoryService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//import java.util.List;
//
//@Controller
//public class CategoryController {
//    private CategoryService categoryService;
//
//
//    // This is token for checking authorization
//
//    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";
//
//    public boolean authorization(String key1) {
//        return key.equals(key1);
//    }
//
//
//
//     // This API just add the chat
//
//    @PostMapping("/add")
//    public ResponseEntity add(@RequestHeader("Authorization") String key1, @RequestBody Category category) {
//        if (authorization(key1) == true) {
//            categoryService.saveCategory(category);
//            return new ResponseEntity(HttpStatus.OK);
//        }else return new ResponseEntity("Not Authorized",HttpStatus.UNAUTHORIZED);
//    }
//
//    @GetMapping("")
//    public ResponseEntity<Object> list(@RequestHeader("Authorization") String key1) {
//        if (authorization(key1) == true) {
//            List<Category> categoryList = categoryService.ListAllCategory();
//            return new ResponseEntity(categoryList, HttpStatus.OK);
//        }
//        else return new ResponseEntity("Not Authorized",HttpStatus.UNAUTHORIZED);
//    }
//
//}
