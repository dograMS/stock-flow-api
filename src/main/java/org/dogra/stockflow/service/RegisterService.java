package org.dogra.stockflow.service;

import jakarta.annotation.Resource;
import org.dogra.stockflow.config.User.CatUserDetails;
import org.dogra.stockflow.config.User.CatUserDetailsService;
import org.dogra.stockflow.model.dto.LoginDTO;
import org.dogra.stockflow.model.dto.LoginResponseDTO;
import org.dogra.stockflow.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService {

    @Resource(name = "userDetailsService")
    private CatUserDetailsService catUserDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder){
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO login(LoginDTO loginData)throws Exception{

        CatUserDetails userDetails = catUserDetailsService.loadUserByUsername(loginData.getUsername());

        if(!passwordEncoder.matches(loginData.getPassword(), userDetails.getPassword())){
            throw new Exception("Wrong Password!!");
        }

        List<String> roles = new ArrayList<>(userDetails.getStaffMember().getRole().size());
        userDetails.getStaffMember().getRole()
                .forEach((role) -> roles.add(role.getTitle().toUpperCase()));


        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setUsername(userDetails.getUsername());
        responseDTO.setRoles(roles);
        responseDTO.setTokens(jwtUtil.genTokens(userDetails));

        return responseDTO;
    }



}
