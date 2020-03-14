package com.kyd3snik.travel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;


/**
 * TODO: добавить историю заказов, наличие скидки
 */
@Entity
public class User {
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
    @ManyToOne
    private City city;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public boolean isHasInternationalPassport() {
        return hasInternationalPassport;
    }

    public void setHasInternationalPassport(boolean hasInternationalPassport) {
        this.hasInternationalPassport = hasInternationalPassport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
