package com.api.vehiclerental.controllers;

import com.api.vehiclerental.dtos.CustomerDto;
import com.api.vehiclerental.dtos.RentalDto;
import com.api.vehiclerental.dtos.RentalJSON;
import com.api.vehiclerental.exceptions.ResponseMessage;
import com.api.vehiclerental.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping
    public ResponseEntity<RentalJSON> addRental(@RequestBody RentalDto rentalDtoToBeAdded) {
        RentalJSON rentalJson = rentalService.addRental(rentalDtoToBeAdded);
        return new ResponseEntity<>(rentalJson, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> getAllRental() {
        List<RentalDto> rentalDtos = rentalService.allRental();
        return new ResponseEntity<>(rentalDtos, HttpStatus.OK);
    }

    @GetMapping("/{rentalId}")
    public ResponseEntity<RentalJSON> getRental(@PathVariable int rentalId) {
        RentalJSON rentalJSON = rentalService.getRental(rentalId);
        return new ResponseEntity<>(rentalJSON, HttpStatus.OK);
    }

    @PatchMapping("/return/{rentalId}")
    public ResponseEntity<RentalDto> returnRental(@PathVariable int rentalId) {
        RentalDto rentalDto = rentalService.returnRental(rentalId);
        return new ResponseEntity<>(rentalDto, HttpStatus.OK);
    }

    @PutMapping("/{rentalId}")
    public ResponseEntity<RentalDto> updateRental(@PathVariable int rentalId, @RequestBody RentalDto rentalDtoTobeUpdated) {
        RentalDto rentalDto = rentalService.updateRental(rentalId, rentalDtoTobeUpdated);
        return new ResponseEntity<>(rentalDto, HttpStatus.OK);
    }

    @DeleteMapping("/{rentalId}")
    public ResponseEntity<ResponseMessage> deleteRental(@PathVariable int rentalId) {
        rentalService.deleteRental(rentalId);

        ResponseMessage response = ResponseMessage.builder()
                .message("Rental Deleted Successfully")
                .success(true)
                .status(HttpStatus.OK)
                .cause(null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
