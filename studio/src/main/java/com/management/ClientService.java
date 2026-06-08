package com.management;

import com.management.dtos.ClientDto;
import com.management.dtos.UpdateRequest;
import com.management.entities.Client;
import com.management.mappers.ClientMapper;
import com.management.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Data
@Service
public class ClientService {
    // repository and mapper
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    // functions
    public ClientDto createClient(ClientDto request) {
        // Transfer DTO into Entity
        var clientDto = clientMapper.toEntity(request);
        // save in depository
        clientRepository.save(clientDto);

        // return DTO
        return clientMapper.toDto(clientDto);
    }

    // GET all client data
    // return: UpdateDto
    public UpdateRequest update(Long id, UpdateRequest request) {
        // existence check
        var target = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "It is not found."));
        // swap data using mapper
        clientMapper.update(request,target);
        // save it
        clientRepository.save(target);
        // return request
        return request;
    }

    // return: List of client data from entity
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Delete
    public void delete(Long id) {
        var target = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, " It is not found."
        ));
        clientRepository.delete(target);
    }
}
