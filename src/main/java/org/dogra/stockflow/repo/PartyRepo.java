package org.dogra.stockflow.repo;


import org.dogra.stockflow.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartyRepo extends JpaRepository<Party, Long> {

    Optional<Party> findByName(String name);

    @Query("SELECT p FROM Party p WHERE p.company = :company")
    List<Party> findByCompany(String company);

    @Query("SELECT p FROM Party p WHERE " +
            "LOWER(p.name) = LOWER(CONCAT('%' , :keyword , '%' )) OR " +
            "CAST(p.phone AS string) = LOWER(CONCAT('%' , :keyword , '%' )) OR " +
            "LOWER(p.email) = LOWER(CONCAT('%' , :keyword , '%' )) OR " +
            "LOWER(p.addressLine) = LOWER(CONCAT('%' , :keyword , '%' ))")
    List<Party> findKeywordMatch(String keyword);

}
