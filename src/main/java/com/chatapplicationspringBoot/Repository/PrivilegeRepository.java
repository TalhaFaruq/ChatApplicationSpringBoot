package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
