package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;

    public Pet save(Pet pet, Long ownerId){
        Customer customer = unwrapCustomer(customerRepository.findById(ownerId), ownerId);
        Pet savedPet = petRepository.save(pet);
        customer.add(savedPet);
        return petRepository.save(savedPet);
    }
    public Pet findPetById(Long id){
        Optional<Pet> pet = petRepository.findById(id);
        return unwrapPet(pet, id);
    }

    public List<Pet> findAllPets() {

        return petRepository.findAll();
    }

    public List<Pet> findPetsByOwnerId(Long ownerId) {
        return petRepository.findByCustomerId(ownerId);
    }
    public List<Pet> findPetsByIds(List<Long> ids){
        return petRepository.findAllById(ids);
    }
    static Customer unwrapCustomer(Optional<Customer> customer, Long id) {
        if (customer.isPresent()) return customer.get();
        else throw new CustomerNotFoundException(id);
    }

    static Pet unwrapPet(Optional<Pet> pet, Long id) {
        if (pet.isPresent()) return pet.get();
        else throw new PetNotFoundException(id);
    }
}
