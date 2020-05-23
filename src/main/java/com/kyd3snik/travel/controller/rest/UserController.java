package com.kyd3snik.travel.controller.rest;

import com.kyd3snik.travel.model.User;
import com.kyd3snik.travel.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Работа с пользователями")
//@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Добавление нового пользователя")
    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @ApiOperation("Получение списка пользователей")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getListUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @ApiOperation("Редактирование пользователя")
    @PutMapping("/users")
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            userService.update(user);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление пользователя")
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
