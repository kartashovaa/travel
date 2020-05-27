package com.kyd3snik.travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResortTransaction {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Resort resort;
}
