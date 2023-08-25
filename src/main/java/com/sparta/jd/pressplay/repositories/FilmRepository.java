package com.sparta.jd.pressplay.repositories;

import com.sparta.jd.pressplay.entities.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
}
