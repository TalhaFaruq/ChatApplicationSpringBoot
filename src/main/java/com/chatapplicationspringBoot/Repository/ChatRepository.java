package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query(value = "SELECT *  from chat c where c.user_id = 1 and c.chat_id = 2", nativeQuery = true)
    String findChatByUserIdAndChatId(long userId, long chatId);
}
