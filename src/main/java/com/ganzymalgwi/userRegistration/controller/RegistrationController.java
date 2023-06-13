package com.ganzymalgwi.userRegistration.controller;

import com.ganzymalgwi.userRegistration.data.dto.request.RegistrationRequest;
import com.ganzymalgwi.userRegistration.data.model.token.VerificationToken;
import com.ganzymalgwi.userRegistration.data.model.user.User;
import com.ganzymalgwi.userRegistration.data.repository.TokenRepository;
import com.ganzymalgwi.userRegistration.event.RegistrationCompleteEvent;
import com.ganzymalgwi.userRegistration.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
public class RegistrationController {


    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final TokenRepository tokenRepository;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest,
                               final HttpServletRequest request){
        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user, applicationUrl(request)));
        return "Registration Successful! Check your emil to confirm your email";
    }

    @GetMapping("/verifyEmail")
    public  String verifyEmail(@RequestParam("token") String token){
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken.getUser().isEnabled()){
            return "This account has already been verified, proceed to login";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully, now you can login to your account";
        }
        return "Invalid verification token";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"
                +request.getServerPort()+request.getContextPath();
    }



}
