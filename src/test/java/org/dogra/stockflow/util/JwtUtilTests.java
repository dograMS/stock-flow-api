package org.dogra.stockflow.util;

import io.jsonwebtoken.Claims;
import jakarta.inject.Inject;
import org.dogra.stockflow.utils.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTests {


    JwtUtil jwtUtil;

    @Inject
    JwtUtilTests(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Test
    void jwtTokenGenTest() {
        String username = "maninder";
        String tokens = jwtUtil.genTokens(username);

        Claims claims = jwtUtil.extractClaims(tokens);

        Assertions.assertEquals(username, claims.getSubject());
    }


}
