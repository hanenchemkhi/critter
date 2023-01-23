package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Long id = scheduleService.save(convertScheduleDTOToSchedule(scheduleDTO));
        return convertScheduleToScheduleDTO(scheduleService.findScheduleById(id));

    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.findAllSchedules().stream()
                .map(this::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.findSchedulesByPetId(petId).stream()
                .map(this::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.findSchedulesByEmployeeId(employeeId).stream()
                .map(this::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.findSchedulesByCustomerId(customerId).stream()
                .map(this::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        return scheduleDTO;
    }
    private Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }



}
