package com.kyd3snik.travel.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class HotelRoom {
    @Id
    @GeneratedValue
    private int id;
    private byte numberOfSleepingPlaces;
    @Enumerated
    @ElementCollection
    private List<Facility> facilities;
    private float cost;

    public byte getNumberOfSleepingPlaces() {
        return numberOfSleepingPlaces;
    }

    public void setNumberOfSleepingPlaces(byte numberOfSleepingPlaces) {
        this.numberOfSleepingPlaces = numberOfSleepingPlaces;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void addFacility(Facility facility) {
        facilities.add(facility);
    }

    public void removeFacility(Facility facility) {
        facilities.remove(facility);
    }

    public void addFacilities(List<Facility> facilities) {
        this.facilities.addAll(facilities);
    }

    public void removeFacilities(List<Facility> facilities) {
        this.facilities.removeAll(facilities);
    }
}
