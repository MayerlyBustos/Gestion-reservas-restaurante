package com.riservi.gestion.app.service.Impl;

import com.riservi.gestion.app.entity.Customer;
import com.riservi.gestion.app.service.ICustomerService;
import com.riservi.gestion.app.service.dtos.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        return null;
    }
}
