package com.kyd3snik.travel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    private static String ROLE_PREFIX = "ROLE_";
    public static String ROLE_USER = "USER";
    public static String ROLE_MODERATOR = "MODERATOR";
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDay;
    private boolean isMale;
    private boolean hasInternationalPassport;
    private String email;
    private String password;
    private float balance;

    private String role;
    @ManyToOne
    private City city;
    private boolean hasDiscount;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + role));
    }

    public boolean isModerator() {
        return role.equals(ROLE_MODERATOR);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
