package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Service.RoleService;
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
//
//    @PostMapping("addrole")
//    public void

}
