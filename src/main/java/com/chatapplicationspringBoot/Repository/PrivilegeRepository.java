package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    @Query(value = "SELECT *  from privilege where status = false", nativeQuery = true)
    public Iterable<Privilege> findByStatus();

}
