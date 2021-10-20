package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);


    @Query(value = "SELECT *  from user where status = false", nativeQuery = true)
    Iterable<User> findByStatus();

    User findByEmailAndToken( String email,int token);

}
