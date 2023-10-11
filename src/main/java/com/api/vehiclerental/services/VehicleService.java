package com.api.vehiclerental.services;

import com.api.vehiclerental.dtos.VehicleDto;

import java.util.List;

public interface VehicleService {

    VehicleDto createUser(VehicleDto vehicleDto);
    VehicleDto getVehicleById(int vehicleId);
    VehicleDto getVehicleByMake(String make);
    VehicleDto getVehicleByMakeAndModel(String make, String model);
    List<VehicleDto> getAllVehicles();
    VehicleDto updateVehicle(int vehicleId, VehicleDto vehicleDto);
    void deleteVehicle(int vehicleId);
}
