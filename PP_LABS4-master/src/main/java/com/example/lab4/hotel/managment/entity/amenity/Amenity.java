package com.example.lab4.hotel.managment.entity.amenity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "amenities")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AmenityType amenityType;

    @Column(nullable = false)
    private int additionalSpace;

    @Enumerated(EnumType.STRING)
    private Category category;
}