package com.ganzymalgwi.userRegistration.service;

import com.ganzymalgwi.userRegistration.data.dto.request.RegistrationRequest;
import com.ganzymalgwi.userRegistration.data.model.token.VerificationToken;
import com.ganzymalgwi.userRegistration.data.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest registrationRequest);

    Optional<User> findByEmail(String email);

    void saveUserVerificationToken(User user, String verificationToken);

    String validateToken(String verificationToken);

}
