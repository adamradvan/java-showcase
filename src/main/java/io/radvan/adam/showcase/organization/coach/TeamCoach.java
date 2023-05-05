package io.radvan.adam.showcase.organization.coach;

import io.radvan.adam.showcase.common.AuditedEntity;
import io.radvan.adam.showcase.organization.Contract;
import io.radvan.adam.showcase.organization.team.Team;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.LinkedHashSet;
import java.util.Set;

import static io.radvan.adam.showcase.common.constants.EntityConstants.BATCH_SIZE;

@Entity
@Table(name = "shwcs_team_coach")
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class TeamCoach extends AuditedEntity {

    private boolean headCoach;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = BATCH_SIZE)
    @CollectionTable(name = "shwcs_coach_roles", joinColumns = @JoinColumn(name = "coach_id"))
    @Column(name = "coach_role")
    private Set<CoachRole> roles = new LinkedHashSet<>();

    @Fetch(FetchMode.SELECT)
    @ManyToOne
    private Team team;

    @Fetch(FetchMode.SELECT)
    @ManyToOne
    private Coach player;

    @NotNull
    @Valid
    @Embedded
    @AttributeOverride(name = "start", column = @Column(name = "contract_start"))
    @AttributeOverride(name = "end", column = @Column(name = "contract_end"))
    @AttributeOverride(name = "signedBy", column = @Column(name = "contract_signatory"))
    private Contract contract;

}