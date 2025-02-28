package org.dogra.stockflow.repo;

import org.dogra.stockflow.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.title = UPPER(:title)")
    Optional<Role> findByTitle(@Param("title") String title);

}
