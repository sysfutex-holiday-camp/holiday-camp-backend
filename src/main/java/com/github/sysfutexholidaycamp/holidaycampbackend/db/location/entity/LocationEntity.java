package com.github.sysfutexholidaycamp.holidaycampbackend.db.location.entity;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserEntity;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Builder
@Table("locations")
public record LocationEntity(
        @Id @Column("id") Long id,

        @Column("name") String name,

        @MappedCollection(idColumn = "location_id") Set<UserEntity> users
) {
}
