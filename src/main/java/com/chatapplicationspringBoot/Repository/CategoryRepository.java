package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Entity.Category;
import com.chatapplicationspringBoot.Model.Entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT *  from category where status = false", nativeQuery = true)
    public Iterable<Category> findByStatus();

}
