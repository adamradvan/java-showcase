package io.radvan.adam.showcase.organization.coach;

import io.radvan.adam.showcase.common.Labeled;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoachRole implements Labeled {
    HEAD_COACH("Head Coach"),
    ASSISTANT_COACH("Assistant Coach"),
    PERSONAL_TRAINER("Personal Trainer");

    private final String label;

}

