package com.github.sysfutexholidaycamp.holidaycampbackend.db.location.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Table("locations")
public record LocationWithoutUsersEntity(
        @Id @Column("id") Long id,

        @Column("name") String name
) {
}
