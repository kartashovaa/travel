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
    @GeneratedValue
    private long id;
    private String title;
    @ManyToOne
    private Country country;
    @ManyToMany
    private List<Entertainment> entertainments;
}
