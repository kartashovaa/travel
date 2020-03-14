package com.kyd3snik.travel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hostel {
    @Id
    @GeneratedValue
    private long id;

}
