package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("FROM Employee e  WHERE :dayOfWeek MEMBER OF e.daysAvailable")
    List<Employee> findEmployeesByDayOfWeek(DayOfWeek dayOfWeek);
}
