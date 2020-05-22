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
    @ManyToOne(fetch = FetchType.LAZY)
    private City departureCity;
    @ManyToOne(fetch = FetchType.LAZY)
    private City arrivalCity;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> tags;
    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;
    @ManyToOne(fetch = FetchType.LAZY)
    private HotelRoom hotelRoom;
    private int durationInDays;
    private Date startDate;
    private Date endDate;
    private float cost;
    private byte personCount;
    private boolean needForForeignPassport;
}
