package com.kyd3snik.travel.services;

import com.kyd3snik.travel.model.Tag;
import com.kyd3snik.travel.repository.TagRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TagService {

    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public void update(Tag tag) {
        boolean exists = tagRepository.existsById(tag.getId());
        if (exists) {
            tagRepository.save(tag);
        } else {
            throw new EntityNotFoundException("Tag not found!");
        }
    }

    public void delete(long id) {
        tagRepository.deleteById(id);
    }
}
