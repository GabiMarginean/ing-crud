package com.ing;

import com.ing.repository.ProductCategoryRepository;
import com.ing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductCategoryRepository categoryRepository;


    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//        User admin = new User()
//                .setUsername("admin")
//                .setPassword(encoder.encode("apassword"))
//                .setRole("ADMIN");
//
//        User observer = new User()
//                .setUsername("observer")
//                .setPassword(encoder.encode("opassword"))
//                .setRole("OBSERVER");
//
//        userRepository.saveAll(List.of(admin, observer));
    }
}
