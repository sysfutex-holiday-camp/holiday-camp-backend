package com.github.sysfutexholidaycamp.holidaycampbackend.core.location.service;

import com.github.sysfutexholidaycamp.holidaycampbackend.core.location.model.LocationWithoutUsersModel;

import java.util.List;

public interface LocationService {
    List<LocationWithoutUsersModel> getAllWithoutUsers();
}
