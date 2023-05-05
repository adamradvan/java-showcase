package io.radvan.adam.showcase.organization.player;


import io.radvan.adam.showcase.common.AuditedEntity;
import io.radvan.adam.showcase.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.radvan.adam.showcase.common.constants.EntityConstants.BATCH_SIZE;

@Entity
@Table(name = "shwcs_player")
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Player extends AuditedEntity {

    @Fetch(FetchMode.SELECT)
    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "person_data_id")
    private Person person;

    @Past
    @NotNull
    private LocalDate draftedAt;
    
    @ElementCollection
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = BATCH_SIZE)
    @CollectionTable(name = "shwcs_player_nicknames", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "nick_name")
    private Set<String> nickNames = new LinkedHashSet<>();

    public long getYearsWhenDrafted() {
        return ChronoUnit.YEARS.between(person.getBirthDay(), draftedAt);
    }

}
