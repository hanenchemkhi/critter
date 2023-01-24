package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public List<Employee> findAvailableEmployees(EmployeeRequestDTO employeeDTO) {

        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        List<Employee> availableEmployees = new ArrayList<>();

        return employeeRepository.findEmployeesByDayOfWeek(dayOfWeek)
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }



    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {

        Employee employee = findEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);

    }

    public List<Employee> findEmployeesByIds(List<Long> ids){
        return employeeRepository.findAllById(ids);
    }
}
