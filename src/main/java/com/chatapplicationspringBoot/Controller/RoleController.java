package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Entity.Role;
import com.chatapplicationspringBoot.Service.RoleService;
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
 * This class contains servlets path. It contains api list all role, find role and delete role.
 * @createdTime 11 October 2021
 */
@EnableSwagger2
@RestController
@RequestMapping("/role")
public class RoleController {

    //Constructor
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * @author Talha Farooq
     * @version 0.3
     * @description This just create logger object to use log in further program
     * @createdTime 11 October 2021
     */
    private static final Logger logger = LogManager.getLogger(RoleController.class);

    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    public boolean authorization(String token){
        return (key.equals(token));
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API get the role from database in ArrayList and shows it in frontend. With Authorization token.
     * @createdTime 11 October 2021
     */
    @GetMapping("/listAll")
    public ResponseEntity<Object> listAllRoles(@RequestHeader("Authorization") String token){
        if(authorization(token))
        return roleService.ListAllRoles();
        else return new ResponseEntity<>("Not Authorize", HttpStatus.NOT_FOUND);

    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API gets the data from frontend in json. With Authorization token. At same time more than one object can be saved.
     * @createdTime 11 October 2021
     */
    @PostMapping("/add")
    public ResponseEntity addRole(@RequestHeader("Authorization") String token, @RequestBody List<Role> roleList) {
        if (authorization(token)) {
            for (Role roles : roleList) {
                roleService.addRole(roles);
            }
            logger.info("Role Added into db",roleList);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity("Not Authorize",HttpStatus.UNAUTHORIZED);
    }

    /**
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.3
     * @description This API delete certain role in the database
     * @createdTime 11 October 2021
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRole(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {
            logger.info("Controller Role Delete");
            return roleService.deleteRolebyId(id);
        } else return new ResponseEntity("Not Authorize", HttpStatus.UNAUTHORIZED);
    }

}
