package org.dogra.stockflow.config;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class SecurityConfigTest {

    private final PasswordEncoder pe;
    Logger logger = LoggerFactory.getLogger(SecurityConfigTest.class);

    public SecurityConfigTest() {
        this.pe = new BCryptPasswordEncoder(12);
    }

    @Test
    void passwordEncoderTest() {

        logger.info("\nPass :{}\nEndcoded : {}", "password", pe.encode("password"));

    }
}