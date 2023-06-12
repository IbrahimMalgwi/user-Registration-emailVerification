package com.ganzymalgwi.userregistrationemailverification.service;

import com.ganzymalgwi.userregistrationemailverification.data.dto.request.RegistrationRequest;
import com.ganzymalgwi.userregistrationemailverification.data.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest registrationRequest);

    Optional<User> findByEmail(String email);
}
