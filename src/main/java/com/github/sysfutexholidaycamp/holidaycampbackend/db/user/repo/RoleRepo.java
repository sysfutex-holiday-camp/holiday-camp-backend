package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.repo;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.RoleEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends ListCrudRepository<RoleEntity, Long> {
}
