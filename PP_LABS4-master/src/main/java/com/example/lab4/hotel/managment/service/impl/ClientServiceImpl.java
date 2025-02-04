package com.example.lab4.hotel.managment.service.impl;

import com.example.lab4.hotel.managment.dto.request.ClientDTO;
import com.example.lab4.hotel.managment.entity.Client;
import com.example.lab4.hotel.managment.exception.UserAlreadyExistException;
import com.example.lab4.hotel.managment.exception.UserNotFoundException;
import com.example.lab4.hotel.managment.repository.ClientRepository;
import com.example.lab4.hotel.managment.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private static final String URL_API_MAIL = "http://localhost:8081/api/v1/mail/send?to=";

    private final ClientRepository clientRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ClientDTO getClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.value(), "Client not found with email: " + email));
        return convertToDTO(client);
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        if(clientRepository.findByEmail(clientDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(HttpStatus.BAD_REQUEST.value(), "User already exist");
        }
        Client client = convertToEntity(clientDTO);
        client.setBalance(BigDecimal.ZERO);
        sendEmailNotification(client.getEmail());
        return convertToDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(String email, ClientDTO clientDTO) {
        Client existingClient = clientRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.value(), "Client not found with email: " + email));

        existingClient.setFirstName(clientDTO.getFirstName());
        existingClient.setLastName(clientDTO.getLastName());
        existingClient.setEmail(clientDTO.getEmail());

        return convertToDTO(clientRepository.save(existingClient));
    }

    @Override
    public void deleteClient(String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.value(), "Client not found with email: " + email));
        clientRepository.delete(client);
    }

    @Override
    public Client findUserByEmail(String email) {
        return clientRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.value(), "Client not found with email: " + email));
    }

    @Override
    @Transactional
    public void withdrawBalance(String email, BigDecimal totalPrice) {
        Client user = findUserByEmail(email);
        user.setBalance(user.getBalance().subtract(totalPrice));
        clientRepository.save(user);
    }

    @Override
    public ClientDTO depositBalance(String email, String balance) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND.value(), "Client not found with email: " + email));

        BigDecimal amount = new BigDecimal(balance);
        client.setBalance(client.getBalance().add(amount));
        Client updatedClient = clientRepository.save(client);
        return convertToDTO(updatedClient);
    }

    private void sendEmailNotification(String recipientEmail) {
        String apiUrl = URL_API_MAIL + recipientEmail;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String emailBody = "{\"message\": \"Welcome to our service!\"}";

        HttpEntity<String> request = new HttpEntity<>(emailBody, headers);

        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(apiUrl, request, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Email sent successfully to: " + recipientEmail);
            } else {
                System.err.println("Failed to send email. Status: " + response.getStatusCode());
            }
        } catch (Exception ex) {
            System.err.println("Error sending email: " + ex.getMessage());
        }
    }

    private ClientDTO convertToDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .balance(client.getBalance())
                .build();
    }

    private Client convertToEntity(ClientDTO clientDTO) {
        return Client.builder()
                .id(clientDTO.getId())
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .email(clientDTO.getEmail())
                .build();
    }
}
