package com.api.vehiclerental.repositories;

import com.api.vehiclerental.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Optional<Vehicle> findByMake(String make);
    Optional<Vehicle> findByMakeAndModel(String make, String model);
}
