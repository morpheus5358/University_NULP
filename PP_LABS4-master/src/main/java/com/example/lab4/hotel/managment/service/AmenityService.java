package com.example.lab4.hotel.managment.service;

import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import com.example.lab4.hotel.managment.repository.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AmenityService {

    List<Amenity> getAllAmenities();

    Optional<Amenity> findById(Long amenityId);
}