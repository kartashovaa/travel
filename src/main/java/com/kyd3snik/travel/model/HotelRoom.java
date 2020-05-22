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
    private long id;
    private byte numberOfSleepingPlaces;
    @Enumerated
    @ElementCollection
    private List<FacilityOld> facilities;
    private float cost;
}
