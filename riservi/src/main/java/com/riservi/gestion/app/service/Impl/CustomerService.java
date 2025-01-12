package com.riservi.gestion.app.service.Impl;

import com.riservi.gestion.app.entity.Customer;
import com.riservi.gestion.app.repository.ICustomerRepository;
import com.riservi.gestion.app.service.ICustomerService;
import com.riservi.gestion.app.service.dtos.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        return null;
    }

    @Override
    public Customer findById(int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.orElse(null);
    }
}
