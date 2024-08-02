package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Table("roles")
public record RoleEntity(
        @Id @Column("id") Long id,

        @Column("name") String name,
        @Column("description") String description
) {
}
