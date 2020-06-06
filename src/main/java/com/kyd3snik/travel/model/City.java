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
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @ManyToOne
    private Country country;
    @ManyToMany
    private List<Entertainment> entertainments;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    List<Hotel> hotels;
    @OneToMany(mappedBy = "departureCity", cascade = CascadeType.ALL)
    List<Resort> resorts;
}
