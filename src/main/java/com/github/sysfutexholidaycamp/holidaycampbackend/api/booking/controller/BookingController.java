package com.github.sysfutexholidaycamp.holidaycampbackend.api.booking.controller;

import com.github.sysfutexholidaycamp.holidaycampbackend.common.booking.converter.BookingConverter;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.booking.service.BookingService;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final BookingConverter bookingConverter;

    private final UserService userService;
}
