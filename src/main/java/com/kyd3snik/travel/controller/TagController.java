package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.model.Tag;
import com.kyd3snik.travel.services.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Работа с тегами")
@RestController
@RequestMapping("/api")
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @ApiOperation("Добавление нового тега")
    @PostMapping("/tags")
    public ResponseEntity<Object> addTag(@RequestBody Tag tag) {
        tagService.addTag(tag);
        return ResponseEntity.ok(tag);
    }

    @ApiOperation("Получение списка тегов")
    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getListTags() {
        return ResponseEntity.ok(tagService.getAll());
    }

    @ApiOperation("Редактирование тега")
    @PutMapping("/tags")
    public ResponseEntity updateTag(@RequestBody Tag tag) {
        try {
            tagService.update(tag);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление тега")
    @DeleteMapping("/tags/{id}")
    public ResponseEntity deleteTag(@PathVariable long id) {
        tagService.delete(id);
        return ResponseEntity.ok().build();
    }
}
