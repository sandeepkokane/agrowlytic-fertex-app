package com.boot.customer.service;

import com.boot.customer.model.dto.CustomerDTO;
import com.boot.customer.model.entity.Customer;
import com.boot.customer.model.mapper.CustomerMapper;
import com.boot.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .parallelStream()
                .map(customerMapper::getDTO)
                .sorted(Comparator.comparing(CustomerDTO::getName))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        final Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customerMapper.getDTO(customer.get());
        }
        return new CustomerDTO();
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        final Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            final Customer customerToUpdate = customer.get();
            customerToUpdate.setName(customerDTO.getName());
            customerToUpdate.setMobileNumber(customerDTO.getMobileNumber());
            return customerMapper.getDTO(customerRepository.save(customerToUpdate));
        } else {
            log.error("No customer found to update with id :: " + id);
            return new CustomerDTO();
        }
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        final Customer customer = customerMapper.getCustomer(customerDTO);
        customer.setAddedDate(LocalDateTime.now());
        return customerMapper.getDTO(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
        final Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
        } else {
            log.error("No Customer found to delete with id :: " + id);
        }
    }

    @Override
    public List<CustomerDTO> searchCustomers(String searchString) {
        return customerRepository.findByNameContaining(searchString)
                .stream()
                .map(customerMapper::getDTO)
                .collect(Collectors.toList());
    }
}
