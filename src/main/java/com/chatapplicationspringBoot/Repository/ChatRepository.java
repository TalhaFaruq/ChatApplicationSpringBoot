package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

}
