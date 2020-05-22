package com.kyd3snik.travel.service;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.request.SignUpRequest;
import com.kyd3snik.travel.services.CityService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final UserDetailsManager detailsManager;
    private final Map<String, com.kyd3snik.travel.model.User> users = new HashMap<>();
    private final CityService cityService;

    public AuthService(@Qualifier("securityManager") UserDetailsManager detailsManager, CityService cityService) {
        this.detailsManager = detailsManager;
        this.cityService = cityService;
        users.put("email@mail.com", new com.kyd3snik.travel.model.User(0,
                "First name",
                "Last name",
                "Middle name",
                Calendar.getInstance().getTime(),
                true,
                true,
                "email@mail.com",
                "password",
                new City(0, "Moscow", null, Collections.emptyList()),
                false));
        users.put("example@mail.com",
                new com.kyd3snik.travel.model.User(0,
                        "First name another",
                        "Last name another",
                        "Middle name another",
                        Calendar.getInstance().getTime(),
                        true,
                        false,
                        "example@mail.com",
                        "pass",
                        new City(0, "Voronezh", null, Collections.emptyList()),
                        true
                ));
    }

    public void signUpUser(SignUpRequest user, WebAuthenticationDetails details) {
        if (detailsManager.userExists(user.getEmail()))
            throw new IllegalStateException("User with this e-mail already exists!");

        com.kyd3snik.travel.model.User user1 = new com.kyd3snik.travel.model.User(0, user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getBirthday(), user.getGender().equals("man"), user.getHasInternationalPassport().equals("yes"), user.getEmail(), user.getPassword(), cityService.getById(user.getCityId()), false);

        users.put(user.getEmail(), user1);
        detailsManager.createUser(new User(user.getEmail(), user.getPassword(), Collections.emptyList()));
        authenticateUser(user, details);
    }

    private void authenticateUser(SignUpRequest user, WebAuthenticationDetails details) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        token.setDetails(details);
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public com.kyd3snik.travel.model.User getUserByEmail(String username) {
        return users.get(username);
    }
}
