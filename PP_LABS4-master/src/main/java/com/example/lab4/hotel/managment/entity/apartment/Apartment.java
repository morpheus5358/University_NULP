package com.example.lab4.hotel.managment.entity.apartment;

import com.example.lab4.hotel.managment.dto.request.UpdatableDTO;
import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import com.example.lab4.hotel.managment.util.UpdaterField;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "apartments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "apartment_amenities",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities;

    protected Apartment(String name, BigDecimal price, Type type, List<Amenity> amenities) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.amenities = amenities;
    }

    public <T extends UpdatableDTO> void updateFields(T requestDTO, UpdaterField updaterField, List<Amenity> amenities) {
        updaterField.updateField(requestDTO.getName(), this::setName);
        updaterField.updateField(requestDTO.getPrice(), this::setPrice);
        updaterField.updateField(requestDTO.getType(), this::setType);
        this.setAmenities(amenities);
    }
}