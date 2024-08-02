package com.github.sysfutexholidaycamp.holidaycampbackend.db.user.repo;

import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserDetailEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.UserDetailWithRolesEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.entity.mapper.UserDetailWithRolesRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDetailRepo extends ListCrudRepository<UserDetailEntity, Long> {
    @Query(
            value = """
                    SELECT ud.user_id AS user_details_user_id,
                           ud.username AS user_details_username,
                           ud.password_hash AS user_details_password_hash,
                           ud.is_account_non_expired AS user_details_is_account_non_expired,
                           ud.is_account_non_locked AS user_details_is_account_non_locked,
                           ud.is_credentials_non_expired AS user_details_is_credentials_non_expired,
                           ud.is_enabled AS user_details_is_enabled,
                           STRING_AGG(CONCAT('{', r.id, ', ', r.name, ', ', r.description, '}'), ', ') AS roles_string
                    FROM user_details ud
                    JOIN users_roles ur ON ud.user_id = ur.user_id
                    JOIN roles r ON ur.role_id = r.id
                    WHERE ud.username = :username
                    GROUP BY ud.user_id,
                             ud.username,
                             ud.password_hash,
                             ud.is_account_non_expired,
                             ud.is_account_non_locked,
                             ud.is_credentials_non_expired,
                             ud.is_enabled
                    """,
            rowMapperClass = UserDetailWithRolesRowMapper.class
    )
    Optional<UserDetailWithRolesEntity> findWithRolesByUsername(@Param("username") String username);
}
