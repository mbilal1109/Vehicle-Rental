package com.api.vehiclerental.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalJSON
{
    private int rentalId;
    private CustomerDto rentedCustomer;
    private VehicleDto rentedVehicle;
    private Date rentedDate;
    private Date returnDate;
    private double rent;
}
