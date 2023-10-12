package com.api.vehiclerental.services.impl;

import com.api.vehiclerental.dtos.VehicleDto;
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
    public VehicleDto createUser(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.save(convertVehicleDtoToVehicle(vehicleDto));
        return convertVehicleToVehicleDto(vehicle);
    }

    @Override
    public VehicleDto getVehicleById(int vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle with given ID not found!"));
        return convertVehicleToVehicleDto(vehicle);
    }

    @Override
    public VehicleDto getVehicleByMake(String make) {
        Vehicle vehicle = vehicleRepository.findByMake(make).orElseThrow(() -> new RuntimeException("Vehicle with given make not found!"));
        return convertVehicleToVehicleDto(vehicle);
    }

    @Override
    public VehicleDto getVehicleByMakeAndModel(String make, String model) {
        Vehicle vehicle = vehicleRepository.findByMakeAndModel(make, model).orElseThrow(() -> new RuntimeException("Vehicle with given make and model not found!"));
        return convertVehicleToVehicleDto(vehicle);
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
        Vehicle vehicleToBeUpdated = vehicleRepository.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle with given ID not found!"));

        vehicleToBeUpdated.setMake(vehicleDto.getMake());
        vehicleToBeUpdated.setModel(vehicleDto.getModel());
        vehicleToBeUpdated.setTrim(vehicleDto.getTrim());
        vehicleToBeUpdated.setYear(vehicleDto.getYear());

        vehicleRepository.save(vehicleToBeUpdated);
        return convertVehicleToVehicleDto(vehicleToBeUpdated);
    }

    @Override
    public void deleteVehicle(int vehicleId) {
        try {
            vehicleRepository.deleteById(vehicleId);
        } catch (Exception e) {
            throw new RuntimeException("Vehicle with given ID not found!" + e);
        }
    }

    private Vehicle convertVehicleDtoToVehicle(VehicleDto vehicleDto) {
        return mapper.map(vehicleDto, Vehicle.class);
    }

    private VehicleDto convertVehicleToVehicleDto(Vehicle vehicle) {
        return mapper.map(vehicle, VehicleDto.class);
    }
}
