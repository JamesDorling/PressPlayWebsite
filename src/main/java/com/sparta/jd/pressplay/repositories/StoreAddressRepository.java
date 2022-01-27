package com.sparta.jd.pressplay.repositories;
;
import com.sparta.jd.pressplay.entities.StoreAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreAddressRepository extends JpaRepository<StoreAddressEntity, Integer> {
}
