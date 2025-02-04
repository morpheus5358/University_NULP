package com.example.lab4.hotel.managment.service;

import com.example.lab4.hotel.managment.dto.request.ClientDTO;
import com.example.lab4.hotel.managment.entity.Client;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

	ClientDTO getClientByEmail(String email);

	ClientDTO createClient(ClientDTO clientDTO);

	ClientDTO updateClient(String email, ClientDTO clientDTO);

	void deleteClient(String email);

	Client findUserByEmail(String email);

	void withdrawBalance(String email, BigDecimal totalPrice);

	ClientDTO depositBalance(String email, String balance);
}
