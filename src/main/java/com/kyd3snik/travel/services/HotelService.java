package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ResortService resortService;

    public HotelService(HotelRepository hotelRepository, ResortService resortService) {
        this.hotelRepository = hotelRepository;
        this.resortService = resortService;
    }

    public void addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public Hotel getById(long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent())
            return hotel.get();
        else
            throw new NoSuchElementException();
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
            throw new EntityNotFoundException("Hotel not found!");
        }
    }

    public void addHotelRoomToHotel(Hotel hotel, HotelRoom hotelRoom) {
        hotel.addHotelRoom(hotelRoom);
    }

    public List<Hotel> findByCity(City city) {
        return hotelRepository.findByCity(city);
    }

    public void delete(long id) {
        User user = AuthService.getUser();
        throwIfCantDelete(user, resortService.findByHotel(getById(id)));

        hotelRepository.deleteById(id);
    }

    public void delete(List<Hotel> hotels) {
        hotels.forEach(hotel -> delete(hotel.getId()));
    }

    private void throwIfCantDelete(User user, List<Resort> resorts) {
        if (user == null)
            throw new IllegalStateException("Пользователь не авторизован!");
        for (Resort resort : resorts) {
            if (resort.isPurchased())
                throw new IllegalStateException("Невозможно удалить отель, т.к. в него были куплены курорты!");
        }
    }
}
