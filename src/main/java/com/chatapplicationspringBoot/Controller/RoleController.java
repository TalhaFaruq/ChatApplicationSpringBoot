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

@EnableSwagger2
@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    public boolean authorization(String token){
        return (key.equals(token));
    }

    @GetMapping("/listAll")
    public ResponseEntity<Object> listAllRoles(@RequestHeader("Authorization") String token){
        if(authorization(token))
        return roleService.ListAllRoles();
        else return new ResponseEntity<>("Blah", HttpStatus.NOT_FOUND);

    }
    @PostMapping("/add")
    public ResponseEntity addRole(@RequestHeader("Authorization") String token, @RequestBody List<Role> roleList) {
        if (authorization(token)) {
            for (Role roles : roleList) {
                roleService.addRole(roles);
            }
            logger.info("Chat Added into db",roleList);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity("Not Authorize",HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRole(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {
            roleService.deleteRolebytID(id);
            logger.info("update");
            return new ResponseEntity("The given ID has been deleted", HttpStatus.OK);
        } else return new ResponseEntity("Not Authorize", HttpStatus.UNAUTHORIZED);
    }

}
