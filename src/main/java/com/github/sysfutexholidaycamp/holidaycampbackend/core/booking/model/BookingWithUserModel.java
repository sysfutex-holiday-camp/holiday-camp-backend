package com.github.sysfutexholidaycamp.holidaycampbackend.core.booking.model;

import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.model.UserWithLocationModel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookingWithUserModel(
        Long id,

        Integer numberOfPeople,

        LocalDateTime createdAt,

        LocalDateTime arrival,
        Boolean isArrived,
        LocalDateTime arrivedAt,

        LocalDateTime departure,
        Boolean isDeparted,
        LocalDateTime departedAt,

        Boolean isConfirmed,
        LocalDateTime confirmedAt,

        Boolean isCanceled,
        LocalDateTime canceledAt,

        UserWithLocationModel user
) {
}
