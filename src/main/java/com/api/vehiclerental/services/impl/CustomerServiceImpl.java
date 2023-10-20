package com.api.vehiclerental.services.impl;

import com.api.vehiclerental.common.VehicleRentalConstant;
import com.api.vehiclerental.dtos.CustomerDto;
import com.api.vehiclerental.dtos.VehicleDto;
import com.api.vehiclerental.exceptions.VehicleRentalNotFoundException;
import com.api.vehiclerental.models.Customer;
import com.api.vehiclerental.models.Vehicle;
import com.api.vehiclerental.repositories.CustomerRepository;
import com.api.vehiclerental.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService
{

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto)
    {
        Customer customer = customerRepository.save(convertCustomerDtoToCustomer(customerDto));
        return convertCustomerToCustomerDto(customer);
    }

    @Override
    public CustomerDto getCustomer(int customerId)
    {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.CUSTOMER_WITH_ID_NOT_FOUND));
        return convertCustomerToCustomerDto(customer);
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.CUSTOMER_WITH_EMAIL_NOT_FOUND));
        return convertCustomerToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for(Customer c : customers) {
            customerDtos.add(convertCustomerToCustomerDto(c));
        }
        return customerDtos;
    }

    @Override
    public CustomerDto updateCustomer(int customerId, CustomerDto customerDto) {
        Customer customerToBeUpdated = customerRepository.findById(customerId).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.CUSTOMER_WITH_ID_NOT_FOUND));

        customerToBeUpdated.setName(customerDto.getName());
        customerToBeUpdated.setEmail(customerDto.getEmail());
        customerToBeUpdated.setMobile(customerDto.getMobile());
        customerToBeUpdated.setLicenseNumber(customerDto.getLicenseNumber());

        customerRepository.save(customerToBeUpdated);
        return convertCustomerToCustomerDto(customerToBeUpdated);
    }

    @Override
    public void deleteCustomer(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new VehicleRentalNotFoundException(VehicleRentalConstant.CUSTOMER_WITH_ID_NOT_FOUND));
        customerRepository.delete(customer);
    }

    private Customer convertCustomerDtoToCustomer(CustomerDto customerDto)
    {
        return mapper.map(customerDto, Customer.class);
    }

    private CustomerDto convertCustomerToCustomerDto(Customer customer)
    {
        return mapper.map(customer, CustomerDto.class);
    }
}
