package com.example.lab4.hotel.managment.repository;

import com.example.lab4.hotel.managment.entity.apartment.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}
