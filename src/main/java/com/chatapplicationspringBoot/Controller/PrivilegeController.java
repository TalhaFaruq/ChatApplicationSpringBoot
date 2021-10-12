package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Entity.Privilege;
import com.chatapplicationspringBoot.Service.PrivilegeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
/**
 * @author Talha Farooq
 * @version 0.3
 * @description This class is Controller class which just takes the data from frontend and give data to frontend. Authorization is check in this class. Also
 * This class contains servlets path. It contains api list all privilege, find privilege and delete privilege.
 * @createdTime 11 October 2021
 */
@EnableSwagger2
@RestController
@RequestMapping("/Privilege")
public class PrivilegeController {
    private PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    /**
     * @author Talha Farooq
     * @version 0.3
     * @description This just create logger object to use log in further program
     * @createdTime 11 October 2021
     */
    private static final Logger logger = LogManager.getLogger(RoleController.class);

    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";//Key for authorization

    //Authorization Function
    public boolean authorization(String token){
        return (key.equals(token));
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the Privileges from database in ArrayList and shows it in frontend. With Authorization token.
     * @createdTime 11 October 2021
     */
    @GetMapping("/listAll")
    public ResponseEntity<Object> listAllPrivileges(@RequestHeader("Authorization") String token){
        if(authorization(token))
            return privilegeService.ListAllPrivilege();
        else return new ResponseEntity<>("Listing all Pivileges", HttpStatus.NOT_FOUND);

    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the data from frontend in json. With Authorization token. At same time more than one object can be saved.
     * @createdTime 11 October 2021
     */
    @PostMapping("/add")
    public ResponseEntity addPrivilege(@RequestHeader("Authorization") String token, @RequestBody List<Privilege> privilegeList) {
        if (authorization(token)) {
            for (Privilege privilege : privilegeList) {
                privilegeService.addPrivilege(privilege);
            }
            logger.info("Privilege Added into db",privilegeList);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity("Not Authorize",HttpStatus.UNAUTHORIZED);
    }


    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API delete certain privilege in the database
     * @createdTime 11 October 2021
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePrivilege(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {
            logger.info("Calling object from service");
            return privilegeService.deletePrivilegebytID(id);
        } else return new ResponseEntity("Not Authorize", HttpStatus.UNAUTHORIZED);
    }

}


