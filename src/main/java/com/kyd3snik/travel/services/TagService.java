package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Tag;
import com.kyd3snik.travel.repository.TagRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    public Tag getById(long id) {
        return tagRepository.findById(id).orElseThrow(() -> new IllegalStateException("Тег не найден"));
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public void update(Tag tag) {
        if (tagRepository.findById(tag.getId()).isPresent()) {
            tagRepository.save(tag);
        } else {
            throw new EntityNotFoundException("Тег не найден");
        }
    }

    public void delete(long id) {
        tagRepository.deleteById(id);
    }
}
