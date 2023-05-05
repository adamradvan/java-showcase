package io.radvan.adam.showcase.sync;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestMappings {

    private static final String DOMAIN_API = "https://free-nba.p.rapidapi.com";

    public static final String TEAMS_API_PATH = DOMAIN_API +"/teams";


}
