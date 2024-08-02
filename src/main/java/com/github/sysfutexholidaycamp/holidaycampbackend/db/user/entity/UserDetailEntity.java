package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Table("user_details")
public record UserDetailEntity(
        @Id @Column("user_id") Long userId,
        @Column("user_id") AggregateReference<UserEntity, Long> userRef,

        @Column("username") String username,
        @Column("password_hash") String passwordHash,

        @Column("is_account_non_expired") Boolean isAccountNonExpired,
        @Column("is_account_non_locked") Boolean isAccountNonLocked,
        @Column("is_credentials_non_expired") Boolean isCredentialsNonExpired,
        @Column("is_enabled") Boolean isEnabled
) {
}
