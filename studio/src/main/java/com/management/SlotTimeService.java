package com.management;

import com.management.dtos.SlotRequest;
import com.management.dtos.SlotResponse;
import com.management.entities.Locations;
import com.management.entities.SlotTime;
import com.management.entities.Trainer;
import com.management.mappers.SlotTimeMapper;
import com.management.repositories.LocationsRepository;
import com.management.repositories.SlotTimeRepository;
import com.management.repositories.TrainerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Data
@Service
public class SlotTimeService {
    // Repositories and Mapper
    private final SlotTimeRepository slotTimeRepository;
    private final TrainerRepository trainerRepository;
    private final LocationsRepository locationsRepository;

    private final SlotTimeMapper slotTimeMapper;

    @Transactional
    public SlotResponse createSlot(Long trainerId, SlotRequest request){
        // validate time
        validateSlotRequest(request);
        // find trainer id
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Trainer is not found")
                );
        // find location id
        Locations location = locationsRepository.findById(request.getLocationId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Location is not found.")
                );
        // slot time check
        validateSlotDoesNotExist(trainerId, request);
        // check and set available status
        if(request.getIsAvailable() == null){
            request.setIsAvailable(true);
        }
        // convert into entity formate
        SlotTime slotTime = slotTimeMapper.toEntity(request, trainer, location);

        // save in repository
        slotTimeRepository.save(slotTime);
        // return response
        return slotTimeMapper.toResponse(slotTime);
    }

    // helper to validate time
    private void validateSlotRequest(SlotRequest request) {
        if (!request.getStartTime().isBefore(request.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        if (!request.getStartTime().toLocalDate().equals(request.getDate())) {
            throw new IllegalArgumentException("Start time date must match slot date");
        }
        if (!request.getEndTime().toLocalDate().equals(request.getDate())) {
            throw new IllegalArgumentException("End time date must match slot date");
        }
    }

    private void validateSlotDoesNotExist(Long trainerId, SlotRequest request) {
        boolean exists = slotTimeRepository.existsByTrainerIdAndLocationIdAndDateAndStartTimeAndEndTime(
                trainerId,
                request.getLocationId(),
                request.getDate(),
                request.getStartTime(),
                request.getEndTime()
        );
        if (exists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This slot time already exists for this trainer and location"
            );
        }
    }



}
