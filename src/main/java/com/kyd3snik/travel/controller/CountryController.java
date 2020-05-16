package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.services.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Работа со странами")
@RestController
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation("Добавление новой страны")
    @PostMapping("/countries")
    public ResponseEntity<Object> addCountry(@RequestBody Country country) {
        countryService.addCountry(country);
        return ResponseEntity.ok(country);
    }

    @ApiOperation("Получение списка стран")
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getListCountries() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @ApiOperation("Редактирование стран")
    @PutMapping("/countries")
    public ResponseEntity updateCountry(@RequestBody Country country) {
        try {
            countryService.update(country);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление страны")
    @DeleteMapping("/countries/{id}")
    public ResponseEntity deleteCountry(@PathVariable long id) {
        countryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
