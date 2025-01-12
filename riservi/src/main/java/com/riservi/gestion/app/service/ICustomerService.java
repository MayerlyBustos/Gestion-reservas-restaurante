package com.riservi.gestion.app.service;

import com.riservi.gestion.app.entity.Customer;
import com.riservi.gestion.app.service.dtos.CustomerDto;

public interface ICustomerService {

    Customer createCustomer(CustomerDto customerDto);

    Customer findById(int customerId);

}
