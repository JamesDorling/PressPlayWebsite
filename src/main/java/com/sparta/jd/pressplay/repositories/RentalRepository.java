package com.sparta.jd.pressplay.repositories;

import com.sparta.jd.pressplay.entities.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {
}
