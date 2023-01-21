package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Long save(Customer customer){
        return customerRepository.save(customer).getId();
    }

    public List<Customer> findAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }
    public Customer findOwnerById(Long id){
        return customerRepository.findById(id).orElse(null);
    }

}
