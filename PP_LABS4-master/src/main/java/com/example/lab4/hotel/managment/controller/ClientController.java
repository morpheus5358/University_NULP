package com.example.lab4.hotel.managment.controller;

import com.example.lab4.hotel.managment.dto.request.ClientDTO;
import com.example.lab4.hotel.managment.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class ClientController {

	private final ClientService clientService;

	@GetMapping("/{email}")
	public ResponseEntity<ClientDTO> getClientByEmail(@PathVariable String email) {
		return ResponseEntity.ok(clientService.getClientByEmail(email));
	}

	@PostMapping
	public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
		return ResponseEntity.ok(clientService.createClient(clientDTO));
	}

	@PutMapping("/{email}")
	public ResponseEntity<ClientDTO> updateClient(@PathVariable String email, @Valid @RequestBody ClientDTO clientDTO) {
		return ResponseEntity.ok(clientService.updateClient(email, clientDTO));
	}

	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deleteClient(@PathVariable String email) {
		clientService.deleteClient(email);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/deposit")
	public ResponseEntity<ClientDTO> depositBalance(@Valid @RequestParam String email,
													@RequestParam String balance) {
		return ResponseEntity.ok(clientService.depositBalance(email, balance));
	}
}
