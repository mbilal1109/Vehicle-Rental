package com.api.vehiclerental.services.impl;

import com.api.vehiclerental.common.VehicleRentalConstant;
import com.api.vehiclerental.dtos.CustomerDto;
import com.api.vehiclerental.dtos.RentalDto;
import com.api.vehiclerental.dtos.RentalJSON;
import com.api.vehiclerental.dtos.VehicleDto;
import com.api.vehiclerental.exceptions.VehicleRentalNotFoundException;
import com.api.vehiclerental.models.Customer;
import com.api.vehiclerental.models.Rental;
import com.api.vehiclerental.models.Vehicle;
import com.api.vehiclerental.repositories.CustomerRepository;
import com.api.vehiclerental.repositories.RentalRepository;
import com.api.vehiclerental.repositories.VehicleRepository;
import com.api.vehiclerental.services.RentalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService
{
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;



    @Override
    public RentalJSON addRental(RentalDto rentalDto) {
        Vehicle vehicle = vehicleRepository.findById(rentalDto.getRentedVehicle()).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.VEHICLE_WITH_ID_NOT_FOUND));

        Customer customer = customerRepository.findById(rentalDto.getRentedCustomer()).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.CUSTOMER_WITH_ID_NOT_FOUND));

        Rental rental=rentalRepository.save(convertRentalDtoToRental(rentalDto));
        RentalJSON rentalJSON=new RentalJSON();
        rentalJSON.setRentalId(rental.getRentalId());
        rentalJSON.setRent(rental.getRent());
        rentalJSON.setReturnDate(rental.getReturnDate());
        rentalJSON.setRentedDate(rental.getRentedDate());
        rentalJSON.setRentedVehicle(mapper.map(vehicle,VehicleDto.class));
        rentalJSON.setRentedCustomer(mapper.map(customer,CustomerDto.class));
        return rentalJSON;
    }

    @Override
    public RentalDto updateRental(int rentalId,RentalDto rentalDto) {
        Rental rentalTobeUpdated = rentalRepository.findById(rentalId).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.RENTAL_WITH_ID_NOT_FOUND));

        rentalTobeUpdated.setRent(rentalDto.getRent());
        rentalTobeUpdated.setRentedDate(rentalDto.getRentedDate());
        rentalTobeUpdated.setReturnDate(rentalDto.getReturnDate());
        rentalTobeUpdated.setRent(rentalDto.getRent());
        rentalTobeUpdated.setRentedVehicle(rentalDto.getRentedVehicle());
        rentalTobeUpdated.setRentedCustomer(rentalDto.getRentedCustomer());

        rentalRepository.save(rentalTobeUpdated);
        return convertRentalToRentalDto(rentalTobeUpdated);
    }

    @Override
    public List<RentalDto> allRental() {
        List<Rental> rentals = rentalRepository.findAll();
        List<RentalDto> rentalDtos = new ArrayList<>();
        for(Rental r : rentals) {
            rentalDtos.add(convertRentalToRentalDto(r));
        }
        return rentalDtos;
    }

    @Override
    public RentalDto returnRental(int rentalId) {
        double price=0;
        Rental rentalTobeUpdated = rentalRepository.findById(rentalId).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.RENTAL_WITH_ID_NOT_FOUND));

        long time_difference = new Date().getTime() - rentalTobeUpdated.getRentedDate().getTime();
        long days_difference = (time_difference / (1000*60*60*24)) % 365;
        price=days_difference > 0 ? days_difference*VehicleRentalConstant.RENT_PER_DAY : VehicleRentalConstant.RENT_PER_DAY;

        rentalTobeUpdated.setReturnDate(new Date());
        rentalTobeUpdated.setRent(price);
        rentalRepository.save(rentalTobeUpdated);
        return convertRentalToRentalDto(rentalTobeUpdated);
    }

    @Override
    public RentalJSON getRental(int rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.RENTAL_WITH_ID_NOT_FOUND));
        Vehicle vehicle = vehicleRepository.findById(rental.getRentedVehicle()).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.VEHICLE_WITH_ID_NOT_FOUND));
        Customer customer = customerRepository.findById(rental.getRentedCustomer()).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.CUSTOMER_WITH_ID_NOT_FOUND));

        RentalJSON rentalJSON=new RentalJSON();
        rentalJSON.setRentalId(rental.getRentalId());
        rentalJSON.setRent(rental.getRent());
        rentalJSON.setReturnDate(rental.getReturnDate());
        rentalJSON.setRentedDate(rental.getRentedDate());
        rentalJSON.setRentedVehicle(mapper.map(vehicle,VehicleDto.class));
        rentalJSON.setRentedCustomer(mapper.map(customer,CustomerDto.class));
        return rentalJSON;
    }

    @Override
    public void deleteRental(int rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.RENTAL_WITH_ID_NOT_FOUND));
        rentalRepository.delete(rental);
    }

    private Rental convertRentalDtoToRental(RentalDto rentalDto)
    {
        return mapper.map(rentalDto, Rental.class);
    }

    private RentalDto convertRentalToRentalDto(Rental rental)
    {
        return mapper.map(rental, RentalDto.class);
    }

}
