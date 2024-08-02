package com.github.sysfutexholidaycamp.holidaycampbackend.core.user.model;

import lombok.Builder;

@Builder
public record RoleModel(
        Long id,

        String name,
        String description
) {
}
