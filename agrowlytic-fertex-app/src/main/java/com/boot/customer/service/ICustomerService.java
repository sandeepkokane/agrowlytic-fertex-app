package com.boot.customer.service;

import com.boot.customer.model.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    String CUSTOMER_LIST = "customer_list";
    String CUSTOMER = "customer";

    String CUSTOMER_FILENAME = "AgrowlyticFertex-Customers.xlsx";

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long id);

    List<CustomerDTO> searchCustomers(String searchString);

}
