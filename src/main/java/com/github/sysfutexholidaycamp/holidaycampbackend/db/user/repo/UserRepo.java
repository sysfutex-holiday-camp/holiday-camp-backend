package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.repo;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserWithLocationEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.mapper.UserWithLocationRowMapper;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.repo.custom.CustomUserRepo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends ListCrudRepository<UserEntity, Long>, CustomUserRepo {
    @Query(
            value = """
                    SELECT u.id AS users_id,
                           u.name AS users_name,
                           u.phone_number AS users_phone_number,
                           u.location_id AS users_location_id,
                           l.id AS locations_id,
                           l.name AS locations_name
                    FROM users u
                    LEFT JOIN locations l ON u.location_id = l.id
                    WHERE u.phone_number = :phoneNumber
                    """,
            rowMapperClass = UserWithLocationRowMapper.class
    )
    Optional<UserWithLocationEntity> findWithLocationByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
