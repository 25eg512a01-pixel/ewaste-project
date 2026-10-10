package com.ewaste.repository;
import com.ewaste.model.AppUser; import com.ewaste.model.Role; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface UserRepository extends JpaRepository<AppUser,Long> { Optional<AppUser> findByEmailIgnoreCase(String email); boolean existsByEmailIgnoreCase(String email); List<AppUser> findByRole(Role role); }
