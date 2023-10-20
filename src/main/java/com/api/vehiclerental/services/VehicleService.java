package com.api.vehiclerental.services;

import com.api.vehiclerental.dtos.VehicleDto;

import java.util.List;

public interface VehicleService {

    VehicleDto createVehicle(VehicleDto vehicleDto);
    VehicleDto getVehicleById(int vehicleId);
    List<VehicleDto> getAllVehiclesByMake(String make);
    List<VehicleDto> getAllVehiclesByMakeAndModel(String make, String model);
    List<VehicleDto> getAllVehicles();
    VehicleDto updateVehicle(int vehicleId, VehicleDto vehicleDto);
    void deleteVehicle(int vehicleId);
}
