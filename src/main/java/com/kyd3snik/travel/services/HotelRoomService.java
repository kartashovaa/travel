package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.HotelRoom;
import com.kyd3snik.travel.repository.HotelRoomRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HotelRoomService {
    private final HotelRoomRepository hotelRoomRepository;

    public HotelRoomService(HotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    public void addHotelRoom(HotelRoom hotelRoom) {
        hotelRoomRepository.save(hotelRoom);
    }

    public HotelRoom getById(long id) {
        Optional<HotelRoom> hotelRoom = hotelRoomRepository.findById(id);
        if (hotelRoom.isPresent())
            return hotelRoom.get();
        else
            throw new NoSuchElementException();
    }

    public List<HotelRoom> getAll() {
        return hotelRoomRepository.findAll();
    }

    public void update(HotelRoom hotelRoom) {
        if (hotelRoomRepository.findById(hotelRoom.getId()).isPresent()) {
            hotelRoomRepository.save(hotelRoom);
        } else {
            throw new EntityNotFoundException("Hotel room not found!");
        }
    }

    public void delete(long id) {
        hotelRoomRepository.deleteById(id);
    }
}
