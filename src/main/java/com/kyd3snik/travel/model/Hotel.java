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
public class Hotel {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    @ManyToOne
    private City city;
    private String address;
    private byte stars;
    @ManyToMany
    private List<HotelRoom> rooms;

    public double getAvgRoomPrice() {
        return rooms.stream().mapToDouble(HotelRoom::getCost).sum() / getRoomsCount();
    }

    public int getRoomsCount() {
        return rooms.size();
    }
}
