package com.kyd3snik.travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRoom {
    @Id
    @GeneratedValue
    private long id;
    private byte numberOfSleepingPlaces;
    @ManyToMany
    private List<Facility> facilities;
    private float cost;
}
