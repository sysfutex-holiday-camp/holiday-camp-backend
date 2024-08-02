package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.mapper;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.location.entity.LocationWithoutUsersEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserWithLocationEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserWithLocationRowMapper implements RowMapper<UserWithLocationEntity> {
    @Override
    public UserWithLocationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return getUser(rs, getLocation(rs));
    }

    private UserWithLocationEntity getUser(ResultSet rs, LocationWithoutUsersEntity location) throws SQLException {
        return UserWithLocationEntity.builder()
                .id(rs.getLong("users_id"))
                .name(rs.getString("users_name"))
                .phoneNumber(rs.getString("users_phone_number"))
                .location(location)
                .build();
    }

    private LocationWithoutUsersEntity getLocation(ResultSet rs) throws SQLException {
        return LocationWithoutUsersEntity.builder()
                .id(rs.getLong("locations_id"))
                .name(rs.getString("locations_name"))
                .build();
    }
}
