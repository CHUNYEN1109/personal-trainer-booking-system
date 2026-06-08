package com.management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne
    @JoinColumn(name = "slot_time_id", nullable = false, unique = true)
    private SlotTime slotTime;

    @Column(name = "booking_time", nullable = false, insertable = false, updatable = false)
    private LocalDateTime bookingTime;

    @Column(name = "booking_status", nullable = false, length = 30)
    private String bookingStatus = "CONFIRMED";

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
