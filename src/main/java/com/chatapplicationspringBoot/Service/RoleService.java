package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Entity.Role;
import com.chatapplicationspringBoot.Repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    private static final Logger logger = LogManager.getLogger(UserService.class);

    public ResponseEntity<Object> ListAllRoles(){
        try {
            List<Role> roleList = roleRepository.findAll();
            logger.info("Getting Roles", roleList);
            return new ResponseEntity<>(roleList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Role doesnot Exist",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> addRole(Role role){
        try{
            roleRepository.save(role);
            logger.info("Role Added");
            return new ResponseEntity("Role Added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Database Not Connected", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> findRoleById(Long id){
        try{
            Optional<Role> roleadded = roleRepository.findById(id);
            logger.info("Role finding by ID");
            return ResponseEntity.ok().body(roleadded);
        }
        catch (Exception e){
            return new ResponseEntity("The role does not Exist",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteRolebytID(long id){
        try {
            roleRepository.deleteById(id);
            logger.info("Deleted Role by"+id);
            return new ResponseEntity("Deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("ID does not Exist",HttpStatus.NOT_FOUND);
        }
    }

}
