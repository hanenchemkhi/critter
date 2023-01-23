package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Long ownerId = petDTO.getOwnerId();
        Pet savedPet = petService.save(convertPetDTOToPet(petDTO),ownerId);
        return convertPetToPetDTO(savedPet);

    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        return convertPetToPetDTO(petService.findPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAllPets();
        return pets.stream()
                .map(this::convertPetToPetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets =petService.findPetsByOwnerId(ownerId);
        return pets.stream()
                .map(this::convertPetToPetDTO)
                .collect(Collectors.toList());

    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;

    }


    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
}
