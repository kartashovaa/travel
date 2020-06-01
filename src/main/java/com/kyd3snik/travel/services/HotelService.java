package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ResortService resortService;
    private final HotelRoomService hotelRoomService;

    public HotelService(HotelRepository hotelRepository, ResortService resortService, HotelRoomService hotelRoomService) {
        this.hotelRepository = hotelRepository;
        this.resortService = resortService;
        this.hotelRoomService = hotelRoomService;
    }

    public void addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public Hotel getById(long id) {
        return hotelRepository.findById(id).get();
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
        Hotel hotel = getById(id);
        List<Resort> resorts = resortService.findByHotel(hotel);
        throwIfCantDelete(user, resorts);

        resortService.delete(resorts);
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
