package com.sparta.jd.pressplay.repositories;

import com.sparta.jd.pressplay.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> { }
