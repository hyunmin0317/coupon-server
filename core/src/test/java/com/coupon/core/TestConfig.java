package com.coupon.core;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.config.name=application-core")
@SpringBootTest(classes = CoreConfiguration.class)
public class TestConfig {
}
