package com.api.vehiclerental.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int rentalId;

    @Column(name = "rented_customer")
    private int rentedCustomer;

    @Column(name = "rented_vehicle")
    private int rentedVehicle;

    @Column(name = "rented_date")
    private Date rentedDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "rental_cost")
    private double rent;
}
