package com.management;

import com.management.dtos.ClientDto;
import com.management.dtos.UpdateRequest;
import com.management.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@Data
@RestController
@RequestMapping("/api/client")
public class ClientController {
    // import/ define services
    private final ClientService clientService;

    // Post
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto request){
        // invoke method from service
        var createClient = clientService.createClient(request);
        // return
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createClient);
    }
    // GET
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> allClients = clientService.getAllClients();
        return ResponseEntity.ok(allClients);
    }

    // UPDATE
    @PutMapping("{id}")
    public ResponseEntity<UpdateRequest> update(
            @PathVariable Long id,
            @RequestBody UpdateRequest request
    ){
        var update = clientService.update(id, request);
        return ResponseEntity.ok(update);
    }

    // DELETE
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
