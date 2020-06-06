package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Hotel;
import com.kyd3snik.travel.model.HotelRoom;
import com.kyd3snik.travel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public Hotel getById(long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new IllegalStateException("Отель не найден"));
    }

    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    public List<Hotel> searchByTitle(String string) {
        return hotelRepository.findByTitleContainingIgnoreCase(string);
    }

    public void update(Hotel hotel) {
        boolean exists = hotelRepository.existsById(hotel.getId());
        if (exists) {
            hotelRepository.save(hotel);
        } else {
            throw new EntityNotFoundException("Отель не найден");
        }
    }

    public void addHotelRoomToHotel(Hotel hotel, HotelRoom hotelRoom) {
        hotel.addHotelRoom(hotelRoom);
    }

    public void delete(long id) {
        try {
            hotelRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalStateException("Невозможно удалить отель, так как в него были куплены курорты");
        }
    }

    public void delete(List<Hotel> hotels) {
        hotels.forEach(hotel -> delete(hotel.getId()));
    }


}
