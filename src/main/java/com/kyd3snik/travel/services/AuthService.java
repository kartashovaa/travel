package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.model.request.SignUpRequest;
import com.kyd3snik.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("Пользователь не найден!");
        return user;
    }

    public void signUpUser(SignUpRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .birthDay(request.getBirthday())
                .password(passwordEncoder.encode(request.getPassword()))
                .isMale(request.getGender().equals("man"))
                .hasInternationalPassport(request.getHasInternationalPassport().equals("yes"))
                .email(request.getEmail())
                .role(User.ROLE_USER)
                .city(cityService.getById(request.getCityId()))
                .hasDiscount(false)
                .build();

        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new IllegalArgumentException("Пользователь с такой почтой уже существует!");

        userRepository.save(user);
        authenticateUser(request);
    }

    public static boolean isAuthenticated() {
        return getUser() != null;
    }

    public static User getUser() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    private void authenticateUser(SignUpRequest user) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
