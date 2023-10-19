package com.api.vehiclerental.controllers;

import com.api.vehiclerental.dtos.CustomerDto;
import com.api.vehiclerental.exceptions.ResponseMessage;
import com.api.vehiclerental.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDtoToBeCreated) {
        CustomerDto customerDto = customerService.addCustomer(customerDtoToBeCreated);
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int customerId) {
        CustomerDto customerDto = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email) {
        CustomerDto customerDto = customerService.getCustomerByEmail(email);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }


    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable int customerId, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = customerService.updateCustomer(customerId, customerDto);
        return new ResponseEntity<>(updatedCustomerDto, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<ResponseMessage> deleteCustomer(@PathVariable int customerId) {
        customerService.deleteCustomer(customerId);

        ResponseMessage response = ResponseMessage.builder()
                .message("Customer Deleted Successfully")
                .success(true)
                .status(HttpStatus.OK)
                .cause(null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
