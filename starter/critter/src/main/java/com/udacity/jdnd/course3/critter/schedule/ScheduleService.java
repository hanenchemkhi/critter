package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    private PetService petService;
    @Autowired
    private EmployeeService employeeService;

    public Schedule save(Schedule schedule, List<Long> petIds, List<Long> employeeIds ) {

        List<Pet> pets = petService.findPetsByIds(petIds);
        List<Employee> employees   = employeeService.findEmployeesByIds(employeeIds);

        schedule.setPets(pets);
        schedule.setEmployees(employees);

        return scheduleRepository.save(schedule);

    }


    public Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findSchedulesByPetId(Long petId) {
        return scheduleRepository.findSchedulesByPetId(petId);
    }

    public List<Schedule> findSchedulesByEmployeeId(Long employeeId) {
        return scheduleRepository.findSchedulesByEmployeeId(employeeId);
    }

    public List<Schedule> findSchedulesByCustomerId(Long customerId) {
        return scheduleRepository.findSchedulesByCustomerId(customerId);
    }
}
