package com.github.sysfutexholidaycamp.holidaycampbackend.db.booking.entity.mapper;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.booking.entity.BookingWithUserEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.location.entity.LocationWithoutUsersEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserWithLocationEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BookingWithUserRowMapper implements RowMapper<BookingWithUserEntity> {
    @Override
    public BookingWithUserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("bookings_id");
        Integer numberOfPeople = rs.getInt("bookings_number_of_people");
        Timestamp createdAt = rs.getTimestamp("bookings_created_at");
        Timestamp arrival = rs.getTimestamp("bookings_arrival");
        Boolean isArrived = rs.getBoolean("bookings_is_arrived");
        Timestamp arrivedAt = rs.getTimestamp("bookings_arrived_at");
        Timestamp departure = rs.getTimestamp("bookings_departure");
        Boolean isDeparted = rs.getBoolean("bookings_is_departed");
        Timestamp departedAt = rs.getTimestamp("bookings_departed_at");
        Boolean isConfirmed = rs.getBoolean("bookings_is_confirmed");
        Timestamp confirmedAt = rs.getTimestamp("bookings_confirmed_at");
        Boolean isCanceled = rs.getBoolean("bookings_is_canceled");
        Timestamp canceledAt = rs.getTimestamp("bookings_canceled_at");

        return BookingWithUserEntity.builder()
                .id(id)
                .numberOfPeople(numberOfPeople)
                .createdAt(createdAt.toLocalDateTime())
                .arrival(arrival.toLocalDateTime())
                .isArrived(isArrived)
                .arrivedAt(arrivedAt == null ? null : arrivedAt.toLocalDateTime())
                .departure(departure.toLocalDateTime())
                .isDeparted(isDeparted)
                .departedAt(departedAt == null ? null : departedAt.toLocalDateTime())
                .isConfirmed(isConfirmed)
                .confirmedAt(confirmedAt == null ? null : confirmedAt.toLocalDateTime())
                .isCanceled(isCanceled)
                .canceledAt(canceledAt == null ? null : canceledAt.toLocalDateTime())
                .user(getUser(rs, getLocation(rs)))
                .build();
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
