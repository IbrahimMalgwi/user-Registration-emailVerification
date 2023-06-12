package com.ganzymalgwi.userregistrationemailverification.service;

import com.ganzymalgwi.userregistrationemailverification.data.dto.request.RegistrationRequest;
import com.ganzymalgwi.userregistrationemailverification.data.model.user.User;
import com.ganzymalgwi.userregistrationemailverification.data.repository.UserRepository;
import com.ganzymalgwi.userregistrationemailverification.execption.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest registrationRequest) {
        Optional<User> user = userRepository.findByEmail(registrationRequest.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("User with email "
                    + registrationRequest.getEmail() + " already  exist");
        }
        var newUser = new User();
        newUser.setFirstName(registrationRequest.getFirstName());
        newUser.setLastName(registrationRequest.getLastName());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        newUser.setRole(registrationRequest.getRole());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
