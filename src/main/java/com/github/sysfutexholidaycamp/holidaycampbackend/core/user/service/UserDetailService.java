package com.github.sysfutexholidaycamp.holidaycampbackend.core.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends UserDetailsService {
    String encodePassword(String rawPassword);

    String getDefaultEncodedPassword();
}
