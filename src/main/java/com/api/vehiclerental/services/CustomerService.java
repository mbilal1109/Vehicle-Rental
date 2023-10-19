package com.api.vehiclerental.services;

import com.api.vehiclerental.dtos.CustomerDto;

import java.util.List;

public interface CustomerService
{
    CustomerDto addCustomer(CustomerDto customerDto);
    CustomerDto getCustomer(int customerId);
    CustomerDto getCustomerByEmail(String email);
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(int customerId,CustomerDto customerDto);
    void deleteCustomer(int customerId);

}
