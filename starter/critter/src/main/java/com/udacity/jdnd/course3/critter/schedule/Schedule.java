package com.udacity.jdnd.course3.critter.schedule;


import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDate date;

    @ElementCollection
    Set<EmployeeSkill> activities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_schedule",
            joinColumns = {@JoinColumn(name = "schedule_id" )},
            inverseJoinColumns = @JoinColumn(name ="employee_id" ))
    List<Employee> employees;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "pet_schedule",
            joinColumns = {@JoinColumn(name= "schedule_id")},
            inverseJoinColumns = @JoinColumn(name = "pet_id"))
    List<Pet> pets = new ArrayList<>();




}
