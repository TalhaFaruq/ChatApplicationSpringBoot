package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Entity.Privilege;
import com.chatapplicationspringBoot.Repository.PrivilegeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeService {
    private PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    private static final Logger logger = LogManager.getLogger(Privilege.class);

    public ResponseEntity<Object> ListAllPrivilege(){
        try {
            List<Privilege> privilegeList = privilegeRepository.findAll();
            logger.info("Getting Privilege", privilegeList);
            return new ResponseEntity<>(privilegeList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Privilege doesnot Exist",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> addPrivilege(Privilege privilege){
        try{
            privilegeRepository.save(privilege);
            logger.info("Privilege Added");
            return new ResponseEntity("Privilege Added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Database Not Connected", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> findPrivilegeById(Long id){
        try{
            Optional<Privilege> privilege = privilegeRepository.findById(id);
            logger.info("Privilege finding by ID");
            return ResponseEntity.ok().body(privilege);
        }
        catch (Exception e){
            return new ResponseEntity("The Privilege does not Exist",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deletePrivilegebytID(long id){
        try {
            privilegeRepository.deleteById(id);
            logger.info("Deleted Privilege by"+id);
            return new ResponseEntity("Deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("ID does not Exist",HttpStatus.NOT_FOUND);
        }
    }
}
