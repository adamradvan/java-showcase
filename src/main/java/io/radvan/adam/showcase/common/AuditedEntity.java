package io.radvan.adam.showcase.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@FieldNameConstants(innerTypeName = "AuditedFields")
public abstract class AuditedEntity extends IdEntity {

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    // TODO: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#auditing.auditor-aware
//    @CreatedBy
//    private User createdBy;
//
//    @LastModifiedBy
//    private User updatedBy;

}
