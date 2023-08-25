package com.sparta.jd.pressplay;

import com.sparta.jd.pressplay.controllers.CustomerController;
import com.sparta.jd.pressplay.controllers.StaffController;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PressPlayApplicationTests {
    @Autowired
    private CustomerController customerController;

    @Autowired
    private StaffController staffController;

    @Test
    void contextLoads() {
    }

    @Test
    void staffControllerLoads() {
        assertThat(staffController).isNotNull();
    }

    @Test
    void customerControllerLoads() {
        assertThat(customerController).isNotNull();
    }
}
