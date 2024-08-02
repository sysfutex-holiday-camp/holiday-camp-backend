package com.github.sysfutexholidaycamp.holidaycampbackend.common.user.converter;

import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.model.RoleModel;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleConverter {
    RoleModel entityToModel(RoleEntity entity);

    List<RoleModel> entitiesToModels(List<RoleEntity> entities);
}
