package com.boot.customer.model.mapper;

import com.boot.customer.model.dto.CustomerDTO;
import com.boot.customer.model.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer getCustomer(CustomerDTO customerDTO) {
        final Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        customer.setLastOrderedDate(customerDTO.getLastOrderedDate());
        return customer;
    }

    public CustomerDTO getDTO(Customer customer) {
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        customerDTO.setAddedDate(customer.getAddedDate());
        customerDTO.setLastOrderedDate(customer.getLastOrderedDate());
        return customerDTO;
    }
}
