package com.kyd3snik.travel.service;

import com.kyd3snik.travel.model.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {
    private final UserDetailsManager detailsManager;

    public AuthService(@Qualifier("securityManager") UserDetailsManager detailsManager) {
        this.detailsManager = detailsManager;
    }

    public void signUpUser(SignUpRequest user, WebAuthenticationDetails details) {
        if (detailsManager.userExists(user.getEmail()))
            throw new IllegalStateException("User with this e-mail already exists!");

        detailsManager.createUser(new User(user.getEmail(), user.getPassword(), Collections.emptyList()));
        authenticateUser(user, details);
    }

    private void authenticateUser(SignUpRequest user, WebAuthenticationDetails details) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        token.setDetails(details);
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
