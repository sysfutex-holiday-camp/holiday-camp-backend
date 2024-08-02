package com.github.sysfutexholidaycamp.holidaycampbackend.common.location.converter;

import com.github.sysfutexholidaycamp.holidaycampbackend.core.location.model.LocationWithoutUsersModel;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.location.entity.LocationWithoutUsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationConverter {
    LocationWithoutUsersModel entityWithoutUsersToModelWithoutUsers(LocationWithoutUsersEntity entity);

    List<LocationWithoutUsersModel> entitiesWithoutUsersToModelWithoutUsers(List<LocationWithoutUsersEntity> entities);
}
