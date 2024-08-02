package com.github.sysfutexholidaycamp.holidaycampbackend.core.user.service;

import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.model.UserWithLocationModel;

import java.util.Optional;

public interface UserService {
    Optional<UserWithLocationModel> getWithLocationByPhoneNumber(String phoneNumber);

    Long createDefaultUser(String name, String phoneNumber, String locationName);
}
