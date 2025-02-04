package com.example.lab4.hotel.managment.entity;

import com.example.lab4.hotel.managment.entity.apartment.Apartment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "apartment_id")
	private Apartment apartment;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Transient
	private int days;

	private BigDecimal pricePerDay;

	public int getDays() {
		return (int) (endDate.toEpochDay() - startDate.toEpochDay());
	}

	public Booking(Client client, Apartment apartment, LocalDate startDate, LocalDate endDate, BigDecimal pricePerDay) {
		this.client = client;
		this.apartment = apartment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pricePerDay = pricePerDay;
	}
}
