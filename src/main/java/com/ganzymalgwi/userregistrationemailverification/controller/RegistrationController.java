package com.ganzymalgwi.userregistrationemailverification.controller;

import com.ganzymalgwi.userregistrationemailverification.data.dto.request.RegistrationRequest;
import com.ganzymalgwi.userregistrationemailverification.data.model.user.User;
import com.ganzymalgwi.userregistrationemailverification.event.RegistrationCompleteEvent;
import com.ganzymalgwi.userregistrationemailverification.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
public class RegistrationController {


    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    @PostMapping
    public String registerUser(RegistrationRequest registrationRequest,
                               final HttpServletRequest request){
        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user, applicationUrl(request)));
        return "Registration Successful! Check your emil to confirm your email";
    }



    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+"+"
                +request.getServerPort()+request.getContextPath();
    }

}
