package com.github.sysfutexholidaycamp.holidaycampbackend.core.user.service.impl;

import com.github.sysfutexholidaycamp.holidaycampbackend.common.user.converter.UserConverter;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.model.UserWithLocationModel;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.service.UserDetailService;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.service.UserService;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserWithLocationEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserConverter userConverter;

    private final UserDetailService userDetailService;

    @Override
    public Optional<UserWithLocationModel> getWithLocationByPhoneNumber(String phoneNumber) {
        UserWithLocationEntity dbUser = userRepo.findWithLocationByPhoneNumber(phoneNumber).orElse(null);
        return Optional.ofNullable(userConverter.entityWithLocationToModelWithLocation(dbUser));
    }

    @Override
    public Long createDefaultUser(String name, String phoneNumber, String locationName) {
        return userRepo.createUser(
                name,
                phoneNumber,
                locationName,
                userDetailService.getDefaultEncodedPassword(),
                new String[] {"ROLE_USER"}
        );
    }
}
