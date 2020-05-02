package com.kyd3snik.travel.model;

import javax.persistence.*;
import java.util.List;

@Entity
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

    public Hotel(String title, City city, String address, byte stars, List<HotelRoom> rooms) {
        this.title = title;
        this.city = city;
        this.address = address;
        this.stars = stars;
        this.rooms = rooms;
    }

    public Hotel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte getStars() {
        return stars;
    }

    public void setStars(byte stars) {
        this.stars = stars;
    }

    public List<HotelRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(HotelRoom room) {
        rooms.add(room);
    }

    public void removeRoom(HotelRoom room) {
        rooms.remove(room);
    }

    public void addRooms(List<HotelRoom> rooms) {
        this.rooms.addAll(rooms);
    }

    public void removeRooms(List<HotelRoom> rooms) {
        this.rooms.removeAll(rooms);
    }

}
