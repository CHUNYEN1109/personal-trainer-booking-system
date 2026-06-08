package com.management;

import com.management.dtos.BookingRequest;
import com.management.dtos.BookingResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BookingController {
    // Services
    private final BookingService bookingService;
    @PostMapping("/{clientId}/bookings")
    public ResponseEntity<BookingResponse> bookingRequest(
            @PathVariable Long clientId
            ,@RequestBody BookingRequest request){
        BookingResponse response = bookingService.requestBooking(clientId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/booking-test")
    public String test() {
        return "Booking controller works";
    }
}
