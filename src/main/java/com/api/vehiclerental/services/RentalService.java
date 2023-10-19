package com.api.vehiclerental.services;

import com.api.vehiclerental.dtos.RentalDto;
import com.api.vehiclerental.dtos.RentalJSON;
import com.api.vehiclerental.models.Rental;

import java.util.List;

public interface RentalService
{
    RentalJSON addRental(RentalDto rentalDto);
    RentalDto updateRental(int rentalId,RentalDto rentalDto);
    List<RentalDto> allRental();
    RentalDto returnRental(int rentalId);
    RentalJSON getRental(int rentalId);
    void deleteRental(int rentalId);
}
