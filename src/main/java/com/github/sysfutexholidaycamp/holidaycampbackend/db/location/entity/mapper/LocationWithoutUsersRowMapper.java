package com.github.sysfutexholidaycamp.holidaycampbackend.db.location.entity.mapper;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.location.entity.LocationWithoutUsersEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationWithoutUsersRowMapper implements RowMapper<LocationWithoutUsersEntity> {
    @Override
    public LocationWithoutUsersEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return LocationWithoutUsersEntity.builder()
                .id(rs.getLong("locations_id"))
                .name(rs.getString("locations_name"))
                .build();
    }
}
