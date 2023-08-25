package com.sparta.jd.pressplay.repositories;

import com.sparta.jd.pressplay.entities.RentalEntity;
import com.sparta.jd.pressplay.entities.ReservedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedRepository extends JpaRepository<ReservedEntity, Integer> {
}
