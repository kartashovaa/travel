package com.kyd3snik.travel.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String hasInternationalPassport;
    private long cityId;
    private String password;
    private String confirmPassword;
    //    @Pattern(regexp = "^(man|woman)$")
    private String gender;
}
