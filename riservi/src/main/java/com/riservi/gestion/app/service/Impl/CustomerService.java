package com.riservi.gestion.app.service.Impl;

import com.riservi.gestion.app.entity.Customer;
import com.riservi.gestion.app.repository.ICustomerRepository;
import com.riservi.gestion.app.service.ICustomerService;
import com.riservi.gestion.app.service.dtos.CustomerDto;
import com.riservi.gestion.app.service.utils.Util;
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
        if (customer.isPresent()) {
            return customer.get();
        }
        return null;
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer saveCustomer(CustomerDto customerDto) {
        return customerRepository.save(Util.mappearCustomer(customerDto));
    }
}
