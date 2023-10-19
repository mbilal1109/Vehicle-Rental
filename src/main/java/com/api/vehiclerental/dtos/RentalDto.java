package com.api.vehiclerental.dtos;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalDto
{
    private int rentalId;
    private int rentedCustomer;
    private int rentedVehicle;
    private Date rentedDate;
    private Date returnDate;
    private double rent;
}
