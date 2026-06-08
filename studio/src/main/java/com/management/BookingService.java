package com.management;

import com.management.dtos.BookingRequest;
import com.management.dtos.BookingResponse;
import com.management.entities.Bookings;
import com.management.entities.Client;
import com.management.entities.SlotTime;
import com.management.entities.Trainer;
import com.management.mappers.BookingMapper;
import com.management.repositories.BookingsRepository;
import com.management.repositories.ClientRepository;
import com.management.repositories.SlotTimeRepository;
import com.management.repositories.TrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@AllArgsConstructor
@Service
public class BookingService {
    // Repositories and Mapper
    private final BookingMapper bookingMapper;
    private final TrainerRepository trainerRepository;
    private final SlotTimeRepository slotTimeRepository;
    private final ClientRepository clientRepository;
    private final BookingsRepository bookingsRepository;

    // function
    @Transactional
    public BookingResponse requestBooking(Long clientId, BookingRequest request) {
//        1. using clientId finds Client
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client is not found."));

//        2. using trainerId finds Trainer
        Trainer trainer = trainerRepository.findById(request.getTrainerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer is not found."));

//        3. using slotTimeId finds SlotTime
        SlotTime slotTime = slotTimeRepository.findById(request.getSlotTimeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot Time is not found."));
//        4. Check if slotTime is available
        if (!slotTime.getIsAvailable()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This slot time is unavailable.");
        }
        // trainer id check
        if (!slotTime.getTrainer().getId().equals(trainer.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This slot time does not belong to the selected trainer");
        }
//        5. Build Bookings entity
        Bookings booking = new Bookings();
        booking.setClient(client);
        booking.setTrainer(trainer);
        booking.setSlotTime(slotTime);
        booking.setBookingStatus("CONFIRMED");

        // make sure it again unavailable
        slotTime.setIsAvailable(false);

//        6. save booking
        Bookings savedBooking = bookingsRepository.save(booking);
        slotTimeRepository.save(slotTime);
//        7. return
        return bookingMapper.toDto(savedBooking);

    }
}