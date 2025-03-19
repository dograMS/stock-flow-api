package org.dogra.stockflow.util;

import io.jsonwebtoken.Claims;
import jakarta.inject.Inject;
import org.dogra.stockflow.config.User.CatUserDetails;
import org.dogra.stockflow.model.Role;
import org.dogra.stockflow.model.Staff;
import org.dogra.stockflow.utils.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTests {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtilTests.class);

    JwtUtil jwtUtil;

    @Inject
    JwtUtilTests(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Test
    void jwtTokenGenTest() {

        CatUserDetails user = new CatUserDetails();
        Staff staff = new Staff();
        staff.setUsername("maninder");
        staff.getRole().add(new Role(1, "USER"));
        staff.getRole().add(new Role(2, "ADMIN"));

        user.setStaffMember(staff);

        String tokens = jwtUtil.genTokens(user);

        Claims claims = jwtUtil.extractClaims(tokens);

        Assertions.assertTrue(claims != null, "extracting tokens assertion failed");

        logger.info((String) claims.get("roles"));

        Assertions.assertEquals(user.getUsername(), claims.getSubject());
    }

    @Test
    void extractClaimsTest(){
        Assertions.assertTrue(jwtUtil.extractClaims("fake.tokens.") == null);
    }


    @Test
    void asjfj(){
        System.out.println(jwtUtil.getSecretKey());
    }



}
