package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.HotelRoom;
import com.kyd3snik.travel.repository.HotelRoomRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
        return hotelRoomRepository.findById(id).orElseThrow(() -> new IllegalStateException("Номер не найден"));
    }

    public List<HotelRoom> getAll() {
        return hotelRoomRepository.findAll();
    }

    public void update(HotelRoom hotelRoom) {
        if (hotelRoomRepository.findById(hotelRoom.getId()).isPresent()) {
            hotelRoomRepository.save(hotelRoom);
        } else {
            throw new EntityNotFoundException("Номер не найден");
        }
    }

    public void delete(long id) {
        hotelRoomRepository.deleteById(id);
    }
}
