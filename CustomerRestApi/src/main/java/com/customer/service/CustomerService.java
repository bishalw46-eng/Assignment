package com.customer.service;

import com.customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    void deleteCustomer(Long id);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
}
