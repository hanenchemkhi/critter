package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        if (petIds!=null){
            customer.setPets(petService.findPetsByIds(petIds));
        }
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }
    public Customer findOwnerById(Long id){
        return customerRepository.findById(id).get();
    }

    public Customer findOwnerByPetId(Long petId) {
        Pet pet = petRepository.findById(petId).get();
        return pet.getCustomer();
    }

}
