package com.ganzymalgwi.userRegistration.data.repository;

import com.ganzymalgwi.userRegistration.data.model.token.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

}
