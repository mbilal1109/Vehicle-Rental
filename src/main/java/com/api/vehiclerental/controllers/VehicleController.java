package com.api.vehiclerental.controllers;

import com.api.vehiclerental.models.Vehicle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        Vehicle v1 = new Vehicle(1, "Toyota", "Camry", "LE", "2023");
        Vehicle v2 = new Vehicle(1, "Toyota", "Camry", "XE", "2023");
        return Arrays.asList(v1, v2);
    }
}
