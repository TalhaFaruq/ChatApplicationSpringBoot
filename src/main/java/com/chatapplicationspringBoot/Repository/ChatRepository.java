package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Entity.Chat;
import com.chatapplicationspringBoot.Model.Entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query(value = "SELECT *  from chat c where c.user_id = 1 and c.chat_id = 2", nativeQuery = true)
    String findChatByUserIdAndChatId(long userId, long chatId);

    @Query(value = "SELECT *  from chat where status = false", nativeQuery = true)
    public Iterable<Chat> findByStatus();
}
