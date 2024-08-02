package com.github.sysfutexholidaycamp.holidaycampbackend.core.location.model;

import lombok.Builder;

@Builder
public record LocationWithoutUsersModel(
        Long id,

        String name
) {
}
