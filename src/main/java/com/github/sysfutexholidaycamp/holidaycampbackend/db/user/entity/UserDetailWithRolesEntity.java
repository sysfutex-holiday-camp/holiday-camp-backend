package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Builder
@Table("user_details")
public record UserDetailWithRolesEntity(
        @Id @Column("user_id") Long userId,

        @Column("username") String username,
        @Column("password_hash") String passwordHash,

        @Column("is_account_non_expired") Boolean isAccountNonExpired,
        @Column("is_account_non_locked") Boolean isAccountNonLocked,
        @Column("is_credentials_non_expired") Boolean isCredentialsNonExpired,
        @Column("is_enabled") Boolean isEnabled,

        Set<RoleEntity> roles
) {
}
