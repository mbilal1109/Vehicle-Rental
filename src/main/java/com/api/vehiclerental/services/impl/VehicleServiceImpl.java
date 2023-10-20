package com.api.vehiclerental.services.impl;

import com.api.vehiclerental.common.VehicleRentalConstant;
import com.api.vehiclerental.dtos.VehicleDto;
import com.api.vehiclerental.exceptions.VehicleRentalNotFoundException;
import com.api.vehiclerental.models.Vehicle;
import com.api.vehiclerental.repositories.VehicleRepository;
import com.api.vehiclerental.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.save(convertVehicleDtoToVehicle(vehicleDto));
        return convertVehicleToVehicleDto(vehicle);
    }

    @Override
    public VehicleDto getVehicleById(int vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.VEHICLE_WITH_ID_NOT_FOUND));
        return convertVehicleToVehicleDto(vehicle);
    }

    @Override
    public List<VehicleDto> getAllVehiclesByMake(String make) {
        List<Vehicle> vehicles = vehicleRepository.findAllByMake(make).get();

        if(vehicles.isEmpty()) {
            throw new VehicleRentalNotFoundException(VehicleRentalConstant.VEHICLE_WITH_MAKE_NOT_FOUND);
        }
        List<VehicleDto> vehicleDtos = new ArrayList<>();
        for(Vehicle v : vehicles) {
            vehicleDtos.add(convertVehicleToVehicleDto(v));
        }
        return vehicleDtos;
    }

    @Override
    public List<VehicleDto> getAllVehiclesByMakeAndModel(String make, String model) {
        List<Vehicle> vehicles = vehicleRepository.findAllByMakeAndModel(make, model).get();

        if(vehicles.isEmpty()) {
            throw new VehicleRentalNotFoundException(VehicleRentalConstant.VEHICLE_WITH_MAKE_MODEL_NOT_FOUND);
        }
        List<VehicleDto> vehicleDtos = new ArrayList<>();
        for(Vehicle v : vehicles) {
            vehicleDtos.add(convertVehicleToVehicleDto(v));
        }
        return vehicleDtos;
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<VehicleDto> vehicleDtos = new ArrayList<>();
        for(Vehicle v : vehicles) {
            vehicleDtos.add(convertVehicleToVehicleDto(v));
        }
        return vehicleDtos;
    }

    @Override
    public VehicleDto updateVehicle(int vehicleId, VehicleDto vehicleDto) {
        Vehicle vehicleToBeUpdated = vehicleRepository.findById(vehicleId).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.VEHICLE_WITH_ID_NOT_FOUND));

        vehicleToBeUpdated.setMake(vehicleDto.getMake());
        vehicleToBeUpdated.setModel(vehicleDto.getModel());
        vehicleToBeUpdated.setTrim(vehicleDto.getTrim());
        vehicleToBeUpdated.setYear(vehicleDto.getYear());

        vehicleRepository.save(vehicleToBeUpdated);
        return convertVehicleToVehicleDto(vehicleToBeUpdated);
    }

    @Override
    public void deleteVehicle(int vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.VEHICLE_WITH_ID_NOT_FOUND));
        vehicleRepository.delete(vehicle);
    }

    private Vehicle convertVehicleDtoToVehicle(VehicleDto vehicleDto) {
        return mapper.map(vehicleDto, Vehicle.class);
    }

    private VehicleDto convertVehicleToVehicleDto(Vehicle vehicle) {
        return mapper.map(vehicle, VehicleDto.class);
    }
}
