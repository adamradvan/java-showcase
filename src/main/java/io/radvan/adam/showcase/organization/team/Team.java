package io.radvan.adam.showcase.organization.team;

import io.radvan.adam.showcase.common.AuditedEntity;
import io.radvan.adam.showcase.common.Labeled;
import io.radvan.adam.showcase.organization.coach.TeamCoach;
import io.radvan.adam.showcase.organization.player.TeamPlayer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static io.radvan.adam.showcase.common.constants.EntityConstants.BATCH_SIZE;

@Builder(toBuilder = true)
@Entity
@Table(name = "shwcs_team")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Team extends AuditedEntity implements Labeled {

    @NotBlank
    private String fullName;

    @NotBlank
    private String abbreviation;

    /**
     * ID in external system
     */
    @NotBlank
    private String externalIdentifier;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Conference conference;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = BATCH_SIZE)
    @OneToMany
    @JoinColumn(name = "team_id")
    private Set<TeamPlayer> allPlayers = new LinkedHashSet<>();

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = BATCH_SIZE)
    @OneToMany
    @JoinColumn(name = "team_id")
    private Set<TeamCoach> coaches = new LinkedHashSet<>();


    public Set<TeamPlayer> getActivePlayers() {
        return allPlayers.stream()
                .filter(TeamPlayer::isActive)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public TeamCoach getHeadCoach() {
        return coaches.stream()
                .filter(TeamCoach::isHeadCoach)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getLabel() {
        return "[" + abbreviation + "] " + fullName;
    }
}
