package io.radvan.adam.showcase.sync;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.radvan.adam.showcase.organization.team.Conference;
import io.radvan.adam.showcase.organization.team.Team;
import io.radvan.adam.showcase.sync.teams.FetchTeamsDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static io.radvan.adam.showcase.sync.RequestMappings.TEAMS_API_PATH;

@Slf4j
@RequiredArgsConstructor
// beaned in SyncConfiguration
public class SyncService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void fetchTeams() {
        Integer nextPage = 1;

        List<Team> allTeams = new ArrayList<>();

        while (nextPage != null) {
            log.info("Executing fetch for page: {}", nextPage);

            String requestUrl = TEAMS_API_PATH + "?page={page}";
            ResponseEntity<FetchTeamsDto> response = restTemplate.getForEntity(requestUrl, FetchTeamsDto.class, nextPage);
            log.info("Fetch executed.");


            if (!response.getStatusCode().is2xxSuccessful()) {
                log.warn("Fetching failed for '{}' with query params: page={}. Response code is not successful but: {}", requestUrl, nextPage, response.getStatusCode().value());
                return;
            }

            FetchTeamsDto responseBody = response.getBody();
            if (responseBody == null) {
                log.warn("Fetching failed for '{}' with query params: page={}. Response body is missing", requestUrl, nextPage);
                return;
            }

            responseBody.getData().stream()
                    .filter(dto -> !dto.getConference().isBlank()) // indicates non-active team
                    .map(this::transformTeam)
                    .forEach(allTeams::add);

            nextPage = responseBody.getMeta().getNextPage();
        }

        log.info("Fetched teams:\n{}", objectMapper.writer().writeValueAsString(allTeams));

        // TODO save teams

    }

    private Team transformTeam(FetchTeamsDto.TeamDto dto) {
        return new Team().toBuilder()
                .abbreviation(dto.getAbbreviation())
                .fullName(dto.getFullName())
                .externalIdentifier(String.valueOf(dto.getId()))
                .conference(Conference.getOrNull(dto.getConference()))
                .build();
    }

    @EventListener
    public void run(ApplicationReadyEvent ignored) {
        log.info("Syncing data...");
        fetchTeams();
        log.info("Sync done.");
    }

    // TODO make data fetch scheduled

}
