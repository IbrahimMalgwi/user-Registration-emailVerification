package com.ganzymalgwi.userRegistration.service;

import com.ganzymalgwi.userRegistration.data.dto.request.RegistrationRequest;
import com.ganzymalgwi.userRegistration.data.model.token.VerificationToken;
import com.ganzymalgwi.userRegistration.data.model.user.User;
import com.ganzymalgwi.userRegistration.data.repository.UserRepository;
import com.ganzymalgwi.userRegistration.data.repository.VerificationTokenRepository;
import com.ganzymalgwi.userRegistration.execption.UserAlreadyExistException;
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
    private final VerificationTokenRepository verificationTokenRepository;
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

    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }
}