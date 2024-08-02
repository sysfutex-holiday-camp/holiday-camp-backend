package com.github.sysfutexholidaycamp.holidaycampbackend.core.user.model;

import com.github.sysfutexholidaycamp.holidaycampbackend.core.location.model.LocationWithoutUsersModel;
import lombok.Builder;

@Builder
public record UserWithLocationModel(
        Long id,

        String name,
        String phoneNumber,

        LocationWithoutUsersModel location
) {
}
