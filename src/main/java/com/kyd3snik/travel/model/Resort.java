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
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<City> path;
    private int durationInDays;
    private Date startDate;
    private Date endDate;
}
