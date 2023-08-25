package com.sparta.jd.pressplay;

import com.sparta.jd.pressplay.entities.StoreAddressEntity;
import com.sparta.jd.pressplay.entities.UserEntity;
import com.sparta.jd.pressplay.repositories.StoreAddressRepository;
import com.sparta.jd.pressplay.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PressPlayApplication {

    private BCryptPasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(PressPlayApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(StoreAddressRepository storeRepository, UserRepository userRepo) {
        //encoder = new BCryptPasswordEncoder();
        return args -> {
            //Locations
            //storeRepository.save(new StoreAddressEntity(0, "Skegness", "PE25", "TBD"));
            //storeRepository.save(new StoreAddressEntity(0, "Stoke-On-Trent", "ST1", "TBD"));
            //storeRepository.save(new StoreAddressEntity(0, "Wells", "BA5", "TBD"));
            //storeRepository.save(new StoreAddressEntity(0, "Salisbury", "SP1", "TBD"));
            //storeRepository.save(new StoreAddressEntity(0, "Ripon", "HG4", "TBD"));

            //Users
            //userRepo.save(new UserEntity());
        };
    }

}
