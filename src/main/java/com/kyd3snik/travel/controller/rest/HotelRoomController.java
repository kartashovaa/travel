package com.kyd3snik.travel.controller.rest;

import com.kyd3snik.travel.model.HotelRoom;
import com.kyd3snik.travel.services.HotelRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Работа с номерами отелей")
@RestController
@RequestMapping("/api")
public class HotelRoomController {

    private HotelRoomService hotelRoomService;

    public HotelRoomController(HotelRoomService hotelRoomService) {
        this.hotelRoomService = hotelRoomService;
    }

    @ApiOperation("Добавление нового номера отеля")
    @PostMapping("/HotelRooms")
    public ResponseEntity<Object> addHotelRoom(@RequestBody HotelRoom hotelRoom) {
        hotelRoomService.addHotelRoom(hotelRoom);
        return ResponseEntity.ok(hotelRoom);
    }

    @ApiOperation("Получение списка номеров отелей")
    @GetMapping("/HotelRooms")
    public ResponseEntity<List<HotelRoom>> getListHotelRooms() {
        return ResponseEntity.ok(hotelRoomService.getAll());
    }

    @ApiOperation("Редактирование номеров отелей")
    @PutMapping("/HotelRooms")
    public ResponseEntity updateHotelRoom(@RequestBody HotelRoom hotelRoom) {
        try {
            hotelRoomService.update(hotelRoom);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление номера отеля")
    @DeleteMapping("/HotelRooms/{id}")
    public ResponseEntity deleteHotelRoom(@PathVariable long id) {
        hotelRoomService.delete(id);
        return ResponseEntity.ok().build();
    }
}
