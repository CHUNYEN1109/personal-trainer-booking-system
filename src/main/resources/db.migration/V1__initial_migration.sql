CREATE TABLE trainer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE client (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE locations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(150) NOT NULL
);

CREATE TABLE slot_time (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    trainer_id BIGINT NOT NULL,
    location_id BIGINT NOT NULL,

    CONSTRAINT fk_slot_time_trainer
        FOREIGN KEY (trainer_id)
        REFERENCES trainer(id),

    CONSTRAINT fk_slot_time_location
        FOREIGN KEY (location_id)
        REFERENCES locations(id),

    CONSTRAINT chk_slot_time_valid
        CHECK (end_time > start_time)
);

CREATE TABLE bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    trainer_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    slot_time_id BIGINT NOT NULL,
    booking_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    booking_status VARCHAR(30) NOT NULL DEFAULT 'CONFIRMED',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_bookings_trainer
        FOREIGN KEY (trainer_id)
        REFERENCES trainer(id),

    CONSTRAINT fk_bookings_client
        FOREIGN KEY (client_id)
        REFERENCES client(id),

    CONSTRAINT fk_bookings_slot_time
        FOREIGN KEY (slot_time_id)
        REFERENCES slot_time(id),

    CONSTRAINT uq_bookings_slot_time
        UNIQUE (slot_time_id)
);