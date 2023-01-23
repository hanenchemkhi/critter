package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Nationalized
    String name;

// Resource : https://stackoverflow.com/questions/416208/jpa-map-collection-of-enums
    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    Set<EmployeeSkill> skills ;

    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    Set<DayOfWeek> daysAvailable;

   @ManyToMany(mappedBy = "employees")
   List<Schedule> schedules;
}
