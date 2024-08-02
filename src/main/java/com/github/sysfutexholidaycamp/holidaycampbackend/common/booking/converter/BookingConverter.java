package com.github.sysfutexholidaycamp.holidaycampbackend.common.booking.converter;

import com.github.sysfutexholidaycamp.holidaycampbackend.common.user.converter.UserConverter;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.booking.model.BookingWithUserModel;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.booking.entity.BookingWithUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserConverter.class}
)
public interface BookingConverter {
    BookingWithUserModel entityWithUserToModelWithUser(BookingWithUserEntity entity);

    List<BookingWithUserModel> entitiesWithUserToModelsWithUser(List<BookingWithUserEntity> entities);
}
