package com.ganzymalgwi.userRegistration.data.repository;

import com.ganzymalgwi.userRegistration.data.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
