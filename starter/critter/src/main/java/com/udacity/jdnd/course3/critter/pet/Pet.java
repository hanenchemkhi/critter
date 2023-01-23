package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import lombok.*;
import lombok.experimental.FieldDefaults;

import org.hibernate.annotations.Nationalized;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Pet {
    @Id
    @GeneratedValue
    Long id;

    @Nationalized
    String name;

    @Enumerated(EnumType.STRING)
    PetType type;
    LocalDate birthDate;
    String notes;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @ManyToMany(mappedBy = "pets")
    List<Schedule> schedules;

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                ", customer=" + customer +
                ", schedules=" + schedules +
                '}';
    }
}
