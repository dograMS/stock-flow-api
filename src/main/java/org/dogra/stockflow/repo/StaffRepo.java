package org.dogra.stockflow.repo;


import org.dogra.stockflow.model.Staff;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepo {

    Optional<Staff> findByUsername(String username);

}
