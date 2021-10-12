package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Entity.Role;
import com.chatapplicationspringBoot.Repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Talha Farooq
 * @version 0.3
 * @Description This class implements logic of API. The Controller send data to their respective service class.
 * This class is role Service class which has show all role, add role and delete role
 * certain ID. Logger is also used to keep tracks of logs whenever any api is called the logs will be saved in
 * file.
 * @creationDate 11 Octuber 2021
 */
@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    private static final Logger logger = LogManager.getLogger(UserService.class);

    /**
     * @return ResponseEntity which return RoleList. and in else it just return not found status
     * @author Talha Farooq
     * @version 0.3
     * @desription This fuction get and show all the RoleList which are saved in database. The data from database
     * comes in list so chatlist.
     * @creationDate 11 Octuber 2021
     */
    public ResponseEntity<Object> ListAllRoles() {
        try {

            List<Role> roleList = (List<Role>) roleRepository.findByStatus();
            if (!roleList.isEmpty()) {
                logger.info("Getting Roles", roleList);
                return new ResponseEntity<>(roleList, HttpStatus.OK);
            } else return new ResponseEntity<>("ID does not exist", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Roles does not Exist", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return only responseEntity Status
     * @author Talha Farooq
     * @version 0.3
     * @description Save role into database by getting values from controller
     * @creationDate 11 Octuber 2021
     */
    public ResponseEntity<Object> addRole(Role role) {
        try {
            roleRepository.save(role);
            LocalDateTime date = LocalDateTime.now();
            role.setCreatedDate(date.toString());
            logger.info("Role Added");
            return new ResponseEntity(role, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Database Not Connected", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return ResponseEntity with one object of role
     * @author Talha Farooq
     * @version 0.3
     * @description Find by ID role from database
     * @creationDate 11 Octuber 2021
     */
    public ResponseEntity<Object> findRoleById(Long id) {
        try {
            Optional<Role> role = roleRepository.findById(id);
            logger.info("Role finding by ID");
            return ResponseEntity.ok().body(role);
        } catch (Exception e) {
            return new ResponseEntity("The role does not Exist", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return ResponseEntity
     * @author Talha Farooq
     * @description Delete role from db
     * @creationDate 11 Octuber 2021
     */
    public ResponseEntity<Object> deleteRolebyId(long id) {
        try {
            Optional<Role> role = roleRepository.findById(id);
            if (role.isPresent()) {
                role.get().setStatus(true);
                roleRepository.save(role.get());
                logger.info("Deleted Role by" + id);
                return new ResponseEntity("Deleted", HttpStatus.OK);
            } else return new ResponseEntity("ID does not Exist", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("ID does not Exist", HttpStatus.NOT_FOUND);
        }
    }

}
