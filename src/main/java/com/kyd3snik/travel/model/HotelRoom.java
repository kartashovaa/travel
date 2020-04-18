package com.kyd3snik.travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRoom {
    @Id
    @GeneratedValue
    private int id;
    private byte numberOfSleepingPlaces;
    @Enumerated
    @ElementCollection
    private List<Facility> facilities;
    private float cost;
}
