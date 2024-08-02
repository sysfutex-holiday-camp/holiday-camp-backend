package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.booking.entity.BookingEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.location.entity.LocationEntity;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Builder
@Table("users")
public record UserEntity(
        @Id @Column("id") Long id,

        @Column("name") String name,
        @Column("phone_number") String phoneNumber,

        @Column("location_id") AggregateReference<LocationEntity, Long> locationRef,

        @MappedCollection(idColumn = "user_id") UserDetailEntity userDetail,
        @MappedCollection(idColumn = "user_id") Set<UserRoleEntity> roles,
        @MappedCollection(idColumn = "user_id") Set<BookingEntity> bookings
) {
}
