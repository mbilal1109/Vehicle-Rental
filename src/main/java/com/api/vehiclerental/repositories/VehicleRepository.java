package com.api.vehiclerental.repositories;

import com.api.vehiclerental.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Optional<List<Vehicle>> findAllByMake(String make);
    Optional<List<Vehicle>> findAllByMakeAndModel(String make, String model);
}
