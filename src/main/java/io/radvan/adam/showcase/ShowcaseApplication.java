package io.radvan.adam.showcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://rapidapi.com/theapiguy/api/free-nba
 */
@SpringBootApplication
public class ShowcaseApplication {

    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(ShowcaseApplication.class, args);
    }

}
