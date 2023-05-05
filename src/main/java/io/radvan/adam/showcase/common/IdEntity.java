package io.radvan.adam.showcase.common;

import io.radvan.adam.showcase.common.id.ULID;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@FieldNameConstants(innerTypeName = "IdFields")
public abstract class IdEntity {

    @Id
    @NotNull
    private String id = ULID.generate();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdEntity idEntity)) return false;
        return Objects.equals(getId(), idEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "'" + getClass().getSimpleName() + "#" + id + "'";
    }
}
