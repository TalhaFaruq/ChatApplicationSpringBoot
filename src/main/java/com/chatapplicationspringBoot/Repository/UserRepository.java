package com.chatapplicationspringBoot.Repository;


import com.chatapplicationspringBoot.Model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //for getting email if it exists in database, it can also be done by not giving @Param
    User findByEmail(@Param(value="email") String email);
}
