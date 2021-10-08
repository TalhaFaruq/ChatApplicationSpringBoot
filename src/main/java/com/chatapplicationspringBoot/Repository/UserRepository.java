package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(@Param(value="email") String email);
    User findByUserIdAndChat(long userId, long chatId);
}
