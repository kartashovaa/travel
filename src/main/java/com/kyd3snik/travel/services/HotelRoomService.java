package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.HotelRoom;
import com.kyd3snik.travel.repository.HotelRoomRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class HotelRoomService {
    private HotelRoomRepository hotelRoomRepository;

    public HotelRoomService(HotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    public void addHotelRoom(HotelRoom hotelRoom) {
        hotelRoomRepository.save(hotelRoom);
    }

    public HotelRoom getById(long id) {
        return hotelRoomRepository.findById(id).get();
    }

    public List<HotelRoom> getAll() {
        return hotelRoomRepository.findAll();
    }

    public void update(HotelRoom hotelRoom) {
        boolean exists = hotelRoomRepository.existsById(hotelRoom.getId());
        if (exists) {
            hotelRoomRepository.save(hotelRoom);
        } else {
            throw new EntityNotFoundException("Hotel room not found!");
        }
    }

    public void delete(long id) {
        hotelRoomRepository.deleteById(id);
    }
}
