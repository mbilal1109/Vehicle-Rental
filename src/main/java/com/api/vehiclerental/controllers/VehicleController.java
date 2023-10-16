package com.api.vehiclerental.controllers;

import com.api.vehiclerental.dtos.VehicleDto;
import com.api.vehiclerental.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDtoToBeCreated) {
        VehicleDto vehicleDto = vehicleService.createUser(vehicleDtoToBeCreated);
        return new ResponseEntity<>(vehicleDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> vehicleDtos = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicleDtos, HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable int vehicleId) {
        VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
        return new ResponseEntity<>(vehicleDto, HttpStatus.OK);
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<List<VehicleDto>> getAllVehicleByMake(@PathVariable String make) {
        List<VehicleDto> vehicleDtos = vehicleService.getAllVehiclesByMake(make);
        return new ResponseEntity<>(vehicleDtos, HttpStatus.OK);
    }

    @GetMapping("/make/{make}/model/{model}")
    public ResponseEntity<List<VehicleDto>> getAllVehicleByMakeAndModel(@PathVariable String make, @PathVariable String model) {
        List<VehicleDto> vehicleDtos = vehicleService.getAllVehiclesByMakeAndModel(make, model);
        return new ResponseEntity<>(vehicleDtos, HttpStatus.OK);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable int vehicleId, @RequestBody VehicleDto vehicleDto) {
        VehicleDto updatedVehicleDto = vehicleService.updateVehicle(vehicleId, vehicleDto);
        return new ResponseEntity<>(updatedVehicleDto, HttpStatus.OK);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>("Vehicle Deleted Successfully", HttpStatus.OK);
    }
}
