package com.github.sysfutexholidaycamp.holidaycampbackend.common.user.converter;

import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.model.UserDetailWithRolesModel;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserDetailWithRolesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RoleConverter.class}
)
public interface UserDetailConverter {
    UserDetailWithRolesModel entityWithRolesToModelWithRoles(UserDetailWithRolesEntity entity);

    List<UserDetailWithRolesModel> entitiesWithRolesToModelsWithRoles(List<UserDetailWithRolesEntity> entities);
}
