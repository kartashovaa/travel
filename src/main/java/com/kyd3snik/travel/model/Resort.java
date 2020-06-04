package com.kyd3snik.travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// Курорт
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resort {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    @ManyToOne
    private City departureCity;
    @ManyToOne
    private City arrivalCity;
    @ManyToMany
    private List<Tag> tags;
    @ManyToOne
    private Hotel hotel;
    private long durationInDays;
    private Date startDate;
    private Date endDate;
    private float cost;
    private byte personCount;
    private boolean isPurchased;
}
