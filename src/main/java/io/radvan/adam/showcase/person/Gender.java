package io.radvan.adam.showcase.person;

import io.radvan.adam.showcase.common.Labeled;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender implements Labeled {
    MALE("Male", "M"),
    FEMALE("Female", "F");

    private final String label;
    private final String code;
}
