package io.radvan.adam.showcase.person;

import io.radvan.adam.showcase.common.AuditedEntity;
import io.radvan.adam.showcase.common.Labeled;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "shwcs_person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Person extends AuditedEntity implements Labeled {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past
    @NotNull
    private LocalDate birthDay;

    @NotNull
    private Gender gender;


    public String getFullName() {
        return firstName + " " + lastName;
    }

    public long getAge() {
        return ChronoUnit.YEARS.between(birthDay, LocalDate.now());
    }

    @Override
    public String getLabel() {
        return getFullName();
    }
}
