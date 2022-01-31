package com.sparta.jd.pressplay.services;

import com.sparta.jd.pressplay.entities.CustomerEntity;
import com.sparta.jd.pressplay.entities.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomerService {
    public static void updateCustomer(CustomerEntity originalCustomer, CustomerEntity updatedCustomer) {
        originalCustomer.setActive(updatedCustomer.getActive());
        originalCustomer.setLastUpdate(TimestampMaker.getCurrentDate());
        originalCustomer.setAddressId(updatedCustomer.getAddressId());
        originalCustomer.setFirstName(updatedCustomer.getFirstName());
        originalCustomer.setLastName(updatedCustomer.getLastName());
        originalCustomer.setEmail(updatedCustomer.getEmail());
    }

    public static void updateUser(UserEntity originalUser, UserEntity updatedUser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!originalUser.getPassword().equals( updatedUser.getPassword())) {
            originalUser.setPassword(encoder.encode(updatedUser.getPassword()));
        }
        originalUser.setEmail(updatedUser.getEmail());
        originalUser.setRole(updatedUser.getRole());
        originalUser.setActive(updatedUser.getActive());
        originalUser.setCustomerId(updatedUser.getCustomerId());
    }
}
