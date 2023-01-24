package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY )
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
   List<Schedule> schedules = new ArrayList<>();
    //    https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/
//    Methods used for synchronizing the bidirectional association.

    public void addSchedule(Schedule schedule){
        schedules.add(schedule);
        schedule.getEmployees().add(this);
    }

}
