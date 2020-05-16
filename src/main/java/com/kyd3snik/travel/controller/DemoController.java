package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.Resort;
import com.kyd3snik.travel.repository.ResortRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "Контроллер для демонстрации работы репозиториев")
@RestController
public class DemoController {
    private ResortRepository resortRepository;

    DemoController(ResortRepository resortRepository) {
        this.resortRepository = resortRepository;
    }

    @ApiOperation("Получение всех курортов")
    @GetMapping("/")
    public List<Resort> getResorts() {
        return resortRepository.findAll();
    }

}
