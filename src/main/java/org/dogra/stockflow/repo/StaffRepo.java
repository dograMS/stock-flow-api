package org.dogra.stockflow.repo;

import org.dogra.stockflow.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {

    Optional<Staff> findByUsername(String username);


    Optional<Staff> findById(Long id);

    @Query("SELECT s FROM Staff s WHERE" +
            " LOWER(s.username) = LOWER(CONCAT('%', :name , '%' ))")
    List<Staff> SearchByName(@Param("name") String name);

    @Query("DELETE FROM Staff s WHERE s.id = :id")
    Optional<Staff> deleteStaffMember(@Param("id")  Long id);

}
