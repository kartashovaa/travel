package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.City;
import com.kyd3snik.travel.services.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Работа с городами")
@RestController
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @ApiOperation("Добавление нового города")
    @PostMapping("/cities")
    public ResponseEntity<Object> addCity(@RequestBody City city) {
        cityService.addCity(city);
        return ResponseEntity.ok(city);
    }

    @ApiOperation("Получение списка городов")
    @GetMapping("/cities")
    public ResponseEntity<List<City>> getListCities() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @ApiOperation("Редактирование города")
    @PutMapping("/cities")
    public ResponseEntity updateCity(@RequestBody City city) {
        try {
            cityService.update(city);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление города")
    @DeleteMapping("/cities/{id}")
    public ResponseEntity deleteCity(@PathVariable long id) {
        cityService.delete(id);
        return ResponseEntity.ok().build();
    }
}
