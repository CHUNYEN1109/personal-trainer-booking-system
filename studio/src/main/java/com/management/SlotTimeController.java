package com.management;

import com.management.dtos.SlotRequest;
import com.management.dtos.SlotResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/trainers/{trainerId}/slotTime")
public class SlotTimeController {
    private final SlotTimeService slotTimeService;
    // CRUD
    // POST Request
    @PostMapping
    public ResponseEntity<SlotResponse> createSlot(
            @PathVariable Long trainerId
            ,@RequestBody SlotRequest request){
        SlotResponse response = slotTimeService.createSlot(trainerId,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
