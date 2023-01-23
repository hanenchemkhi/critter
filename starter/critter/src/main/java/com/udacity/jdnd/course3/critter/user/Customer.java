package com.udacity.jdnd.course3.critter.user;
import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    Long id;

    @Nationalized
    String name;
    String phoneNumber;

    String notes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true)
    List<Pet> pets = new ArrayList<>();;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", pets=" + pets +
                '}';
    }
}