package org.dogra.stockflow.service;

import jakarta.transaction.Transactional;
import org.dogra.stockflow.model.Role;
import org.dogra.stockflow.model.Staff;
import org.dogra.stockflow.repo.RoleRepo;
import org.dogra.stockflow.repo.StaffRepo;
import org.dogra.stockflow.utils.UserNotFoundException;
import org.dogra.stockflow.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class StaffService {

    private final StaffRepo repo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(StaffService.class);

    @Autowired
    public StaffService(StaffRepo staffRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {

        this.repo = staffRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;

        logger.debug("Staff Service init");
    }


    public void addStaffMember(Staff member) throws Exception {

        if (!Util.checkPasswordStregth(member.getPassword())) {
            throw new Exception("Password not strong enough");
        }

        String hashedPass = passwordEncoder.encode(member.getPassword());

        member.setPassword(hashedPass);

        Staff newMember = repo.save(member);

        logger.debug("New Staff Member added : " + newMember);
    }

    @Transactional
    public void assignRole(Long id, Long roleId) throws Exception {
        Staff member = repo.findById(id).orElseThrow(UserNotFoundException::new);

        Role role = roleRepo.findById(roleId).orElseThrow(() ->
                new Exception("Selected Role Not Present"));

        member.getRole().add(role);
        Staff updatedMember = repo.save(member);

        logger.debug("assigning new role : " + updatedMember);
    }

    @Transactional
    public void revokeRole(Long staffId, Long roleId) throws Exception {
        Staff member = repo.findById(staffId).orElseThrow(UserNotFoundException::new);

        AtomicReference<Boolean> found = new AtomicReference<>(false);
        member.getRole().removeIf( role -> {
            if(role.getId() == roleId){
                found.set(true);
                return true;
            }
            return false;
        });

        if(!found.get()){
            logger.debug("requested to revoke role but role not found staff_id: " + staffId + " ,roleId: " + roleId);
            throw new Exception("Nothing to Revoke, Role Not assigned");
        }

        repo.save(member);
    }

    @Transactional
    public void deleteStaffMember(Long staffId) throws UserNotFoundException {

        Staff member = repo.deleteStaffMember(staffId).orElseThrow(() ->{
            logger.debug("deleteStaffMember failed with id: {}", staffId);
            return new UserNotFoundException();
        });

        logger.debug("deleteStaffMember with id: {}", staffId);
    }

}
