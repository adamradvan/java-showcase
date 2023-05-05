package io.radvan.adam.showcase.organization.player;

import io.radvan.adam.showcase.common.AuditedEntity;
import io.radvan.adam.showcase.organization.Contract;
import io.radvan.adam.showcase.organization.team.Team;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "shwcs_team_player")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class TeamPlayer extends AuditedEntity {

    private boolean active;

    @Fetch(FetchMode.SELECT)
    @ManyToOne
    private Team team;

    @Fetch(FetchMode.SELECT)
    @ManyToOne
    private Player player;

    @NotBlank
    private String jerseyCode;

    @NotNull
    @Valid
    @Embedded
    @AttributeOverride(name = "start",    column = @Column(name = "contract_start"))
    @AttributeOverride(name = "end",      column = @Column(name = "contract_end"))
    @AttributeOverride(name = "signedBy", column = @Column(name = "contract_signatory"))
    private Contract contract;

}
