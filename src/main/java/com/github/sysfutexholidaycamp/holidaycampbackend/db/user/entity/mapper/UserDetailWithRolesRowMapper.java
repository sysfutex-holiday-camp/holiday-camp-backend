package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.mapper;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.RoleEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserDetailWithRolesEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDetailWithRolesRowMapper implements RowMapper<UserDetailWithRolesEntity> {
    @Override
    public UserDetailWithRolesEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long userId = rs.getLong("user_details_user_id");
        String username = rs.getString("user_details_username");
        String passwordHash = rs.getString("user_details_password_hash");
        Boolean isAccountNonExpired = rs.getBoolean("user_details_is_account_non_expired");
        Boolean isAccountNonLocked = rs.getBoolean("user_details_is_account_non_locked");
        Boolean isCredentialsNonExpired = rs.getBoolean("user_details_is_credentials_non_expired");
        Boolean isEnabled = rs.getBoolean("user_details_is_enabled");

        Set<RoleEntity> roles = new HashSet<>();

        String rolesString = rs.getString("roles_string");
        if (rolesString != null && !rolesString.isEmpty()) {
            for (final String roleString : rolesString.split("}, \\{")) {
                String[] roleStringParts = roleString.replace("{", "").replace("}", "").split(", ");
                if (roleStringParts.length == 3) {
                    roles.add(RoleEntity.builder()
                            .id(Long.parseLong(roleStringParts[0]))
                            .name(roleStringParts[1])
                            .description(roleStringParts[2])
                            .build());
                }
            }
        }

        return UserDetailWithRolesEntity.builder()
                .userId(userId)
                .username(username)
                .passwordHash(passwordHash)
                .isAccountNonExpired(isAccountNonExpired)
                .isAccountNonLocked(isAccountNonLocked)
                .isCredentialsNonExpired(isCredentialsNonExpired)
                .isEnabled(isEnabled)
                .roles(roles)
                .build();
    }
}
