package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.model.request.SignUpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService implements UserDetailsService {
    private final Map<String, User> users = new HashMap<>();
    private final CityService cityService;

    public static User getUser() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isAuthorized() {
        return getUser() != null;
    }

    public AuthService(CityService cityService) {
        this.cityService = cityService;
        users.put("email@mail.com", new User(0,
                "First name",
                "Last name",
                "Middle name",
                Calendar.getInstance().getTime(),
                true,
                true,
                "email@mail.com",
                "password",
                User.ROLE_MODERATOR,
                new City(0, "Moscow", null, Collections.emptyList()),
                false));
        users.put("example@mail.com",
                new User(0,
                        "First name another",
                        "Last name another",
                        "Middle name another",
                        Calendar.getInstance().getTime(),
                        true,
                        false,
                        "example@mail.com",
                        "pass",
                        User.ROLE_USER,
                        new City(0, "Voronezh", null, Collections.emptyList()),
                        true
                ));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = users.get(username);
            return user;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username not found!", e);
        }
    }

    public void signUpUser(SignUpRequest user) {
//        if (detailsManager.userExists(user.getEmail()))
//            throw new IllegalStateException("User with this e-mail already exists!");

        User user1 = new User(0, user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getBirthday(), user.getGender().equals("man"), user.getHasInternationalPassport().equals("yes"), user.getEmail(), user.getPassword(), User.ROLE_USER, cityService.getById(user.getCityId()), false);

        users.put(user.getEmail(), user1);
//        detailsManager.createUser(new User(user.getEmail(), user.getPassword(), Collections.emptyList()));
        authenticateUser(user);
    }

    private void authenticateUser(SignUpRequest user) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public com.kyd3snik.travel.model.User getUserByEmail(String username) {
        return users.get(username);
    }
}
