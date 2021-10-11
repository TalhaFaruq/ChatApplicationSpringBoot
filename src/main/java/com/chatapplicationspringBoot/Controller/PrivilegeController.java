package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Entity.Privilege;
import com.chatapplicationspringBoot.Model.Entity.Role;
import com.chatapplicationspringBoot.Service.PrivilegeService;
import com.chatapplicationspringBoot.Service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PrivilegeController {
    private PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    public boolean authorization(String token){
        return (key.equals(token));
    }

    @GetMapping("/listAll")
    public ResponseEntity<Object> listAllPrivileges(@RequestHeader("Authorization") String token){
        if(authorization(token))
            return privilegeService.ListAllPrivilege();
        else return new ResponseEntity<>("Listing all Pivileges", HttpStatus.NOT_FOUND);

    }

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePrivilege(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {
            privilegeService.deletePrivilegebytID(id);
            logger.info("Deleted");
            return new ResponseEntity("The given ID has been deleted", HttpStatus.OK);
        } else return new ResponseEntity("Not Authorize", HttpStatus.UNAUTHORIZED);
    }

}


