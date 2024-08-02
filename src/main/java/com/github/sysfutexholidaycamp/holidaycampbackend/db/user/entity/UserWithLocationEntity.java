package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.location.entity.LocationWithoutUsersEntity;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Table("users")
public record UserWithLocationEntity(
        @Id @Column("id") Long id,

        @Column("name") String name,
        @Column("phone_number") String phoneNumber,

        LocationWithoutUsersEntity location
) {
}
