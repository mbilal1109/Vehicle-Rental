package com.api.vehiclerental.services;

import com.api.vehiclerental.dtos.VehicleDto;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    VehicleDto createUser(VehicleDto vehicleDto);
    VehicleDto getVehicleById(int vehicleId);
    List<VehicleDto> getAllVehiclesByMake(String make);
    List<VehicleDto> getAllVehiclesByMakeAndModel(String make, String model);
    List<VehicleDto> getAllVehicles();
    VehicleDto updateVehicle(int vehicleId, VehicleDto vehicleDto);
    void deleteVehicle(int vehicleId);
}
