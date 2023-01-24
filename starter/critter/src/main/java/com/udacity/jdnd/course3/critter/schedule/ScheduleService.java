package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.exception.ScheduleNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return unwrapSchedule(schedule, id);
    }

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findSchedulesByPetId(Long petId) {
        return scheduleRepository.findByPetsId(petId);
    }

    public List<Schedule> findSchedulesByEmployeeId(Long employeeId) {
       return scheduleRepository.findByEmployeesId(employeeId);
    }

    public List<Schedule> findSchedulesByCustomerId(Long customerId) {
        return scheduleRepository.findByCustomersId(customerId);
    }
    static Schedule unwrapSchedule(Optional<Schedule> schedule, Long id) {
        if (schedule.isPresent()) return schedule.get();
        else throw new ScheduleNotFoundException(id);
    }
}
