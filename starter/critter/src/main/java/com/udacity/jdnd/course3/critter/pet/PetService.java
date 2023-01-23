package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        Customer customer = customerService.findOwnerById(ownerId);

        pet.setCustomer(customer);

        Pet createdPet = petRepository.save(pet);

        List<Pet> customerPets = customer.getPets();

        if(customerPets == null) customerPets = new ArrayList<>();

        customerPets.add(createdPet);
        customer.setPets(customerPets);

        customerRepository.save(customer);


        return createdPet;
    }
    public Pet findPetById(Long id){

        return petRepository.findById(id).get();
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
}
