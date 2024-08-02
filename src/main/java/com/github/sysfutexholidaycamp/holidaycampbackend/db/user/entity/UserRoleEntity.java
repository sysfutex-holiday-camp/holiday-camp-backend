package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity;

import lombok.Builder;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Table("users_roles")
public record UserRoleEntity(
        @Column("role_id") AggregateReference<RoleEntity, Long> roleRef
) {
}
