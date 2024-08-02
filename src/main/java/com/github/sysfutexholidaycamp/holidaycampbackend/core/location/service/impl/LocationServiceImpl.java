package com.github.sysfutexholidaycamp.holidaycampbackend.core.location.service.impl;

import com.github.sysfutexholidaycamp.holidaycampbackend.common.location.converter.LocationConverter;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.location.model.LocationWithoutUsersModel;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.location.service.LocationService;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.location.repo.LocationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepo locationRepo;
    private final LocationConverter locationConverter;

    @Override
    public List<LocationWithoutUsersModel> getAllWithoutUsers() {
        return locationConverter.entitiesWithoutUsersToModelWithoutUsers(locationRepo.findAllWithoutUsers());
    }
}
