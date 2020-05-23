package com.kyd3snik.travel.model.request;

import com.kyd3snik.travel.base.validators.fields_match.FieldsMatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldsMatch(fields = {"password", "confirmPassword"}, message = "Пароли не совпадают!")
public class SignUpRequest {
    @NotBlank(message = "Почта не может быть пустой!")
    private String email;

    @Size(min = 2, max = 32, message = "Имя не может быть пустым!")
    private String firstName;

    @Size(min = 2, max = 32, message = "Фамилия не может быть пустым!")
    private String lastName;

    @Size(min = 2, max = 32, message = "Отчество не может быть пустым!")
    private String middleName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Не верная дата рождения")
    private Date birthday;
    @Pattern(regexp = "^(yes|no)$", message = "Нерерное значение наличия загранпаспорта!")
    private String hasInternationalPassport;
    @Min(value = 0, message = "Не валидные город!")
    private long cityId;

    @Size(min = 6, message = "Длина пароля должна быть от 6")
    private String password;

    @Size(min = 6, message = "Длина пароля должна быть от 6")
    private String confirmPassword;
    @Pattern(regexp = "^(man|woman)$")
    private String gender;
}
