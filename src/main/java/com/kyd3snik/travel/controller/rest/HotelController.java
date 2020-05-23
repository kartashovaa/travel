package com.kyd3snik.travel.controller.rest;

import com.kyd3snik.travel.model.Hotel;
import com.kyd3snik.travel.services.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Работа с отелями")
////@RestController
@RequestMapping("/api")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @ApiOperation("Добавление нового отеля")
    @PostMapping("/Hotels")
    public ResponseEntity<Object> addHotel(@RequestBody Hotel hotel) {
        hotelService.addHotel(hotel);
        return ResponseEntity.ok(hotel);
    }

    @ApiOperation("Получение списка отелей")
    @GetMapping("/Hotels")
    public ResponseEntity<List<Hotel>> getListHotels() {
        return ResponseEntity.ok(hotelService.getAll());
    }

    @ApiOperation("Редактирование отелей")
    @PutMapping("/Hotels")
    public ResponseEntity updateHotel(@RequestBody Hotel hotel) {
        try {
            hotelService.update(hotel);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление отеля")
    @DeleteMapping("/Hotels/{id}")
    public ResponseEntity deleteHotel(@PathVariable long id) {
        hotelService.delete(id);
        return ResponseEntity.ok().build();
    }
}
