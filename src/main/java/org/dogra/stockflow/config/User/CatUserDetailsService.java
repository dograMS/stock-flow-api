package org.dogra.stockflow.config.User;

import org.dogra.stockflow.model.Staff;
import org.dogra.stockflow.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CatUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepo repo;

    @Override
    public CatUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Staff staffMember = repo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Staff Member not found")
        );

        return new CatUserDetails(staffMember);
    }
}
