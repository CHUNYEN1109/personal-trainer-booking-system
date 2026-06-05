package com.management;

import com.management.dtos.TrainerDto;
import com.management.dtos.UpdateRequest;
import com.management.entities.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Data
@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    // Get request test
    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    // Post request test
    @PostMapping
    public ResponseEntity<TrainerDto> createTrainer(@RequestBody TrainerDto dto) {
        TrainerDto createdTrainer = trainerService.createTrainer(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTrainer);
    }

    // Put request test
    @PutMapping("/{id}")
    public ResponseEntity<UpdateRequest> updateTrainer(
            @PathVariable Long id,
            @RequestBody UpdateRequest request
    ) {

        var updatedTrainer = trainerService.updateTrainer(id, request);
        return ResponseEntity.ok(updatedTrainer);
    }

    // Delete request test
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
         trainerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
