package com.api.vehiclerental.repositories;

import com.api.vehiclerental.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Vehicle findByMake(String make);
    Vehicle findByMakeAndModel(String make, String model);
}
