package com.github.sysfutexholidaycamp.holidaycampbackend.core.user.service.impl;

import com.github.sysfutexholidaycamp.holidaycampbackend.common.user.converter.UserDetailConverter;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.service.UserDetailService;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.user.repo.UserDetailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {
    @Value("${holiday-camp.security.default-password}")
    private String DEFAULT_RAW_PASSWORD;

    private final UserDetailRepo userDetailRepo;
    private final UserDetailConverter userDetailConverter;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailConverter.entityWithRolesToModelWithRoles(
                userDetailRepo
                        .findWithRolesByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Пользователь с номером телефона " + username + " не найден"))
        );
    }

    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public String getDefaultEncodedPassword() {
        return encodePassword(DEFAULT_RAW_PASSWORD);
    }
}
