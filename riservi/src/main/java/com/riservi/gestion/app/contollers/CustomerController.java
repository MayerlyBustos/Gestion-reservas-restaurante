package com.riservi.gestion.app.contollers;

import com.riservi.gestion.app.entity.Customer;
import com.riservi.gestion.app.service.ICustomerService;
import com.riservi.gestion.app.service.dtos.CustomerDto;
import com.riservi.gestion.app.service.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("riservi/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getByCustomerId(@PathVariable("customerId") Integer customerId){
        Customer customer = customerService.findById(customerId);
        if (customer != null) {
            return ResponseEntity.ok(Util.mappearCustomerDto(customer));
        }
        return ResponseEntity.notFound().build();
    }
}
