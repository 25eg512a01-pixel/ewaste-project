        package com.ewaste.config;

import com.ewaste.model.AppUser;
import com.ewaste.model.Role;
import com.ewaste.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SeedAdmin {

 @Bean
 CommandLineRunner createInitialAdmin(
         UserRepository users,
         PasswordEncoder encoder,
         @Value("${app.admin.name:}") String name,
         @Value("${app.admin.email:}") String email,
         @Value("${app.admin.password:}") String password) {

  return args -> {
   if (!name.isBlank()
           && !email.isBlank()
           && !password.isBlank()
           && !users.existsByEmailIgnoreCase(email)) {

    if (password.length() < 8) {
     throw new IllegalArgumentException(
             "ADMIN_PASSWORD must be at least 8 characters");
    }

    AppUser user = new AppUser();
    user.setName(name);
    user.setEmail(email.toLowerCase());
    user.setPassword(encoder.encode(password));
    user.setRole(Role.ADMIN);

    users.save(user);
   }
  };
 }
}
