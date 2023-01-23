package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("FROM Pet p WHERE p.id = :petId")
    List<Schedule> findSchedulesByPetId(Long petId);

    @Query("FROM Employee e WHERE e.id = :employeeId")
    List<Schedule> findSchedulesByEmployeeId(Long employeeId);
    @Query("FROM Schedule s JOIN s.pets sp JOIN sp.customer c WHERE c.id = :customerId")
    List<Schedule> findSchedulesByCustomerId(Long customerId);
}
