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
public class Country {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    List<City> cities;
}
