package io.radvan.adam.showcase.organization;

import io.radvan.adam.showcase.person.Person;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
@FieldNameConstants
public class Contract {

    @NotNull
    private LocalDate start;

    @NotNull
    private LocalDate end;

    @NotNull
    @Fetch(FetchMode.SELECT)
    @OneToOne(optional = false)
    @JoinColumn(name = "signer_id")
    private Person signedBy;

}
