package com.management.repositories;

import com.management.entities.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository<Locations, Long>{
}
