package com.ganzymalgwi.userregistrationemailverification.data.repository;

import com.ganzymalgwi.userregistrationemailverification.data.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
