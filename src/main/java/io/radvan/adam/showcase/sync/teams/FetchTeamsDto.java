package io.radvan.adam.showcase.sync.teams;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON from <a href="https://rapidapi.com/theapiguy/api/free-nba">Free NBA API</a>
 * <p>
 * DTO represents a part of the response JSON
 * <pre>
 * {
 *   "data": [
 *     {
 *       "id": 1,
 *       "abbreviation": "ATL",
 *       "city": "Atlanta",
 *       "conference": "East",
 *       "division": "Southeast",
 *       "full_name": "Atlanta Hawks",
 *       "name": "Hawks"
 *     },
 *     ...
 *   ],
 *   "meta": {
 *     "total_pages": 2,
 *     "current_page": 1,
 *     "next_page": 2,
 *     "per_page": 30,
 *     "total_count": 45
 *   }
 * }
 * </pre>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public final class FetchTeamsDto {

    private List<TeamDto> data = new ArrayList<>();

    private FetchMetadataDto meta;

    @Data
    public static final class TeamDto {

        @JsonProperty(value = "id", required = true)
        private long id = 1;

        @JsonProperty(value = "abbreviation", required = true)
        private String abbreviation = "ATL";

        // Possibly blank -> indicates team is no longer active
        @JsonProperty(value = "conference", required = true)
        private String conference = "East";

        @JsonProperty(value = "full_name", required = true)
        private String fullName = "Atlanta Hawks";

    }

    @Data
    public static final class FetchMetadataDto {

        @JsonProperty(value = "total_pages", required = true)
        private int totalPages;

        @JsonProperty(value = "current_page", required = true)
        private int currentPage;

        @JsonProperty(value = "next_page", required = true)
        private Integer nextPage;

    }
}
