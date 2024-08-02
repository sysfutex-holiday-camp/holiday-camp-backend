package com.github.sysfutexholidaycamp.holidaycampbackend.core.booking.service;

import com.github.sysfutexholidaycamp.holidaycampbackend.core.booking.model.BookingWithUserModel;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    List<BookingWithUserModel> getAllWithUser();

    List<BookingWithUserModel> getAllWithUserByUserPhoneNumber(String phoneNumber);

    List<BookingWithUserModel> getAllWithUserByArrivalDate(LocalDateTime from, LocalDateTime to);

    void create(String phoneNumber, Integer numberOfPeople, LocalDateTime arrival, LocalDateTime departure) throws UserNotFoundException;

    void cancel(Long bookingId);

    void confirm(Long bookingId);

    void arrive(Long bookingId);

    void depart(Long bookingId);
}
