package com.github.sysfutexholidaycamp.holidaycampbackend.common.user.converter;

import com.github.sysfutexholidaycamp.holidaycampbackend.common.location.converter.LocationConverter;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.model.UserWithLocationModel;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserWithLocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {LocationConverter.class}
)
public interface UserConverter {
    UserWithLocationModel entityWithLocationToModelWithLocation(UserWithLocationEntity entity);

    List<UserWithLocationModel> entitiesWithLocationToModelsWithLocation(List<UserWithLocationEntity> entities);
}
