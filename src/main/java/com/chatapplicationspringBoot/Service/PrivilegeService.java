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

/**
 * @author Talha Farooq
 * @version 0.3
 * @Description This class implements logic of API. The Controller send data to their respective service class.
 * This class is privilege Service class which has show all privileges, add privilege and delete privilege
 * certain ID. Logger is also used to keep tracks of logs whenever any api is called the logs will be saved in
 * file.
 * @creationDate 11 October 2021
 */
@Service
public class PrivilegeService {

    private PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    private static final Logger logger = LogManager.getLogger(Privilege.class);

    /**
     * @return ResponseEntity which return privilege list and in else it just return not found status
     * @author Talha Farooq
     * @version 0.3
     * @desription This function get and show all the privilege which are saved in database. The data from database
     * comes in list so chatlist.
     * @creationDate 11 Octuber 2021
     */
    public ResponseEntity<Object> ListAllPrivilege(){
        try {
            List<Privilege> privilegeList = privilegeRepository.findAll();
            logger.info("Getting Privilege", privilegeList);
            return new ResponseEntity<>(privilegeList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Privilege doesnot Exist",HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return only responseEntity Status
     * @author Talha Farooq
     * @version 0.3
     * @description Save privilege into database by getting values from controller
     * @creationDate 11 Octuber 2021
     */
    public ResponseEntity<Object> addPrivilege(Privilege privilege){
        try{
            privilegeRepository.save(privilege);
            logger.info("Privilege Added");
            return new ResponseEntity("Privilege Added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Database Not Connected", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return ResponseEntity with one object of privilege
     * @author Talha Farooq
     * @version 0.3
     * @description Find by ID privilege from database
     * @creationDate 11 Octuber 2021
     */
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

    /**
     * @return ResponseEntity
     * @author Talha Farooq
     * @description Delete privilege from db
     * @creationDate 11 Octuber 2021
     */
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
