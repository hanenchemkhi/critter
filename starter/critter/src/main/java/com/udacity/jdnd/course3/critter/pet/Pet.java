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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    @Nationalized
    String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private PetType type;
    LocalDate birthDate;
    String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pet_schedule",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    List<Schedule> schedules;



}
