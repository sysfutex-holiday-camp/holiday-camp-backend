package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.repo.custom;

import org.jetbrains.annotations.NotNull;

public interface CustomUserRepo {
    Long createUser(
            @NotNull String name,
            @NotNull String phoneNumber,
            @NotNull String locationName,
            @NotNull String passwordHash,
            @NotNull String[] roleNames
    );
}
