package com.example.lab4.hotel.managment.repository;

import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}