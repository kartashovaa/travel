package com.kyd3snik.travel.repository;

import com.kyd3snik.travel.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
