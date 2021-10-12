package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {

    @Query(value = "SELECT *  from role where status = false", nativeQuery = true)
    public Iterable<Role> findByStatus();
}
