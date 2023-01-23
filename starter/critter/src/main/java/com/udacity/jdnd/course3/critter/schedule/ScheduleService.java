package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public Long save(Schedule schedule) {
        return scheduleRepository.save(schedule).getId();
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
