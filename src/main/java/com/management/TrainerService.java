package com.management;

import com.management.dtos.TrainerDto;
import com.management.dtos.UpdateRequest;
import com.management.entities.Trainer;
import com.management.mappers.TrainerMapper;
import com.management.repositories.TrainerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@AllArgsConstructor
@Data
@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    // for Get Test
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    // for Post Test
    public TrainerDto createTrainer(TrainerDto dto) {
        // transfer DTO into Entity
        Trainer trainer = trainerMapper.toEntity(dto);
        // save in Repository
        Trainer savedTrainer = trainerRepository.save(trainer);

        return trainerMapper.toDto(savedTrainer);
    }

    // for Put Test
    public UpdateRequest updateTrainer(Long id, UpdateRequest request) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Not Found"
                ));

        trainerMapper.update(request, trainer);
        // save
        trainerRepository.save(trainer);
        return request;
    }

    // for Delete Test
    public void delete(Long id){
        var trainer = trainerRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "It is not found"
                )
                );
        trainerRepository.delete(trainer);
    }



}
