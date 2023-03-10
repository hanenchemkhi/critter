package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetService petService;

    public Customer save(Customer customer, List<Long> petIds){
        List<Pet> pets = petService.findPetsByIds(petIds);
        customer.setPets(pets);
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }
    public Customer findOwnerById(Long id){
        return customerRepository.findById(id).get();

    }

    public Customer findOwnerByPetId(Long id) {
        Pet pet =  petRepository.findById(id).get();
        return pet.getCustomer();
    }

}
