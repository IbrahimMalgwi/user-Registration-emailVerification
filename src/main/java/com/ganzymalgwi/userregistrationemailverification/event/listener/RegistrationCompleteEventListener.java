package com.ganzymalgwi.userregistrationemailverification.event.listener;

import com.ganzymalgwi.userregistrationemailverification.data.model.user.User;
import com.ganzymalgwi.userregistrationemailverification.event.RegistrationCompleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener
        implements ApplicationListener<RegistrationCompleteEvent> {

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //TODO
        // 1. Get the newly registered user
        User user = event.getUser();
        //TODO 2. Create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();
        //TODO 3. Save the verification token for the user

        //TODO 4. Build the verification url to be sent to the user
        String url = event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
        //TODO 5. Send the email
        log.info("Click the link to verify your registration : {}", url);

    }
}
