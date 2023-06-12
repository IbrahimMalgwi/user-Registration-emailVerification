package com.ganzymalgwi.userregistrationemailverification.controller;

import com.ganzymalgwi.userregistrationemailverification.data.dto.request.RegistrationRequest;
import com.ganzymalgwi.userregistrationemailverification.data.model.user.User;
import com.ganzymalgwi.userregistrationemailverification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
public class RegistrationController {


    private final UserService userService;

    public String registerUser(RegistrationRequest registrationRequest){
        User user = userService.registerUser(registrationRequest);
        return "Registration Successful! Check your emil to confirm your email";
    }

}
