package io.radvan.adam.showcase.organization.coach;

import io.radvan.adam.showcase.common.AuditedEntity;
import io.radvan.adam.showcase.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;

@Entity
@Table(name = "shwcs_coach")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Coach extends AuditedEntity {

    @Fetch(FetchMode.SELECT)
    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "person_data_id")
    private Person person;

    @PositiveOrZero
    private int wonChampionships;

    @NotNull
    private LocalDate careerStart;

}
