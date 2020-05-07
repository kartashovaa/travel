package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.services.ResortService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(description  = "Работа с курортами")
@RestController
public class ResortController {

    private ResortService resortService;

    public ResortController(ResortService resortService) {
        this.resortService = resortService;
    }

    //@ApiOperation("Добавление нового курорта")
    @PostMapping("/resorts")
    public ResponseEntity<Object> addResort(@RequestBody Resort resort) {
        resortService.addResort(resort);
        return ResponseEntity.ok(resort);
    }

    //@ApiOperation("Получение списка курортов")
    @GetMapping("/resorts")
    public ResponseEntity<List<Resort>> getListResorts() {
        return ResponseEntity.ok(resortService.getAll());
    }

    //@ApiOperation("Редактирование курорта")
    @PutMapping("/resorts")
    public ResponseEntity updateResort(@RequestBody Resort resort) {
        try {
            resortService.update(resort);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //@ApiOperation("Удаление курорта")
    @DeleteMapping("/resorts/{id}")
    public ResponseEntity deleteResort(@PathVariable long id) {
        resortService.delete(id);
        return ResponseEntity.ok().build();
    }
}
