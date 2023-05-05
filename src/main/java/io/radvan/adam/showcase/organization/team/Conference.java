package io.radvan.adam.showcase.organization.team;

import io.radvan.adam.showcase.common.Labeled;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Conference implements Labeled {
    WEST("West Conference"),
    EAST("East Conference");

    private final String label;

}
