package com.github.sysfutexholidaycamp.holidaycampbackend.db.booking.repo;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.booking.entity.BookingEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.booking.entity.BookingWithUserEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.booking.entity.mapper.BookingWithUserRowMapper;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepo extends ListCrudRepository<BookingEntity, Long> {
    @Query(
            value = """
                    SELECT b.id AS bookings_id,
                           b.user_id AS bookings_user_id,
                           b.number_of_people AS bookings_number_of_people,
                           b.created_at AS bookings_created_at,
                           b.arrival AS bookings_arrival,
                           b.is_arrived AS bookings_is_arrived,
                           b.arrived_at AS bookings_arrived_at,
                           b.departure AS bookings_departure,
                           b.is_departed AS bookings_is_departed,
                           b.departed_at AS bookings_departed_at,
                           b.is_confirmed AS bookings_is_confirmed,
                           b.confirmed_at AS bookings_confirmed_at,
                           b.is_canceled AS bookings_is_canceled,
                           b.canceled_at AS bookings_canceled_at,
                           u.id AS users_id,
                           u.name AS users_name,
                           u.phone_number AS users_phone_number,
                           u.location_id AS users_location_id,
                           l.id AS locations_id,
                           l.name AS locations_name
                    FROM bookings b
                    JOIN users u ON b.user_id = u.id
                    LEFT JOIN locations l ON u.location_id = l.id
                    ORDER BY b.arrival
                    """,
            rowMapperClass = BookingWithUserRowMapper.class
    )
    List<BookingWithUserEntity> findAllWithUser();

    @Query(
            value = """
                    SELECT b.id AS bookings_id,
                           b.user_id AS bookings_user_id,
                           b.number_of_people AS bookings_number_of_people,
                           b.created_at AS bookings_created_at,
                           b.arrival AS bookings_arrival,
                           b.is_arrived AS bookings_is_arrived,
                           b.arrived_at AS bookings_arrived_at,
                           b.departure AS bookings_departure,
                           b.is_departed AS bookings_is_departed,
                           b.departed_at AS bookings_departed_at,
                           b.is_confirmed AS bookings_is_confirmed,
                           b.confirmed_at AS bookings_confirmed_at,
                           b.is_canceled AS bookings_is_canceled,
                           b.canceled_at AS bookings_canceled_at,
                           u.id AS users_id,
                           u.name AS users_name,
                           u.phone_number AS users_phone_number,
                           u.location_id AS users_location_id,
                           l.id AS locations_id,
                           l.name AS locations_name
                    FROM bookings b
                    JOIN users u ON b.user_id = u.id
                    LEFT JOIN locations l ON u.location_id = l.id
                    WHERE u.phone_number = :phoneNumber
                    ORDER BY b.arrival
                    """,
            rowMapperClass = BookingWithUserRowMapper.class
    )
    List<BookingWithUserEntity> findAllWithUserByUserPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(
            value = """
                    SELECT b.id AS bookings_id,
                           b.user_id AS bookings_user_id,
                           b.number_of_people AS bookings_number_of_people,
                           b.created_at AS bookings_created_at,
                           b.arrival AS bookings_arrival,
                           b.is_arrived AS bookings_is_arrived,
                           b.arrived_at AS bookings_arrived_at,
                           b.departure AS bookings_departure,
                           b.is_departed AS bookings_is_departed,
                           b.departed_at AS bookings_departed_at,
                           b.is_confirmed AS bookings_is_confirmed,
                           b.confirmed_at AS bookings_confirmed_at,
                           b.is_canceled AS bookings_is_canceled,
                           b.canceled_at AS bookings_canceled_at,
                           u.id AS users_id,
                           u.name AS users_name,
                           u.phone_number AS users_phone_number,
                           u.location_id AS users_location_id,
                           l.id AS locations_id,
                           l.name AS locations_name
                    FROM bookings b
                    JOIN users u ON b.user_id = u.id
                    LEFT JOIN locations l ON u.location_id = l.id
                    WHERE b.arrival BETWEEN :from AND :to
                    ORDER BY b.arrival
                    """,
            rowMapperClass = BookingWithUserRowMapper.class
    )
    List<BookingWithUserEntity> findAllWithUserByArrival(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Modifying
    @Query("""
            UPDATE bookings
            SET is_canceled = TRUE
            WHERE id = :id
            """)
    void setIsCanceled(@Param("id") Long id);

    @Modifying
    @Query("""
            UPDATE bookings
            SET is_confirmed = TRUE
            WHERE id = :id
            """)
    void setIsConfirmed(@Param("id") Long id);

    @Modifying
    @Query("""
            UPDATE bookings
            SET is_arrived = TRUE
            WHERE id = :id
            """)
    void setIsArrived(@Param("id") Long id);

    @Modifying
    @Query("""
            UPDATE bookings
            SET is_departed = TRUE
            WHERE id = :id
            """)
    void setIsDeparted(@Param("id") Long id);
}
