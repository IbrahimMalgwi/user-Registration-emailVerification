package com.ganzymalgwi.userRegistration.data.repository;

import com.ganzymalgwi.userRegistration.data.model.token.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
