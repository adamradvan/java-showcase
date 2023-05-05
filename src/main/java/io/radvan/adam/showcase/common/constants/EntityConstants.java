package io.radvan.adam.showcase.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityConstants {
    /**
     * Used in {@link org.hibernate.annotations.BatchSize} annotations
     */
    public static final int BATCH_SIZE = 100;
}
