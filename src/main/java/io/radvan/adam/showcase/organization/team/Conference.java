package io.radvan.adam.showcase.organization.team;

import io.radvan.adam.showcase.common.Labeled;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Conference implements Labeled {
    WEST("West Conference", "West"),
    EAST("East Conference", "East");

    private final String label;
    private final String code;


    private static final Map<String, Conference> ENUM_MAP = Arrays.stream(Conference.values())
            .collect(Collectors.toUnmodifiableMap(
                    instance -> instance.name().toLowerCase(),
                    Function.identity())
            );

    public static Conference getOrNull(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public static Conference get(String name) {
        if (!ENUM_MAP.containsKey(name.toLowerCase())) {
            throw new RuntimeException(String.format("Conference with name '%s' not found.", name));
        }
        return ENUM_MAP.get(name.toLowerCase());
    }


}
