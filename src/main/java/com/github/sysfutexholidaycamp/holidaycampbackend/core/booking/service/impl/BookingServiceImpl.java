package com.github.sysfutexholidaycamp.holidaycampbackend.core.booking.service.impl;

import com.github.sysfutexholidaycamp.holidaycampbackend.common.booking.converter.BookingConverter;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.booking.model.BookingWithUserModel;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.booking.service.BookingService;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.exception.UserNotFoundException;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.model.UserWithLocationModel;
import com.github.sysfutexholidaycamp.holidaycampbackend.core.user.service.UserService;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.booking.entity.BookingEntity;
import com.github.sysfutexholidaycamp.holidaycampbackend.db.booking.repo.BookingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final BookingConverter bookingConverter;

    private final UserService userService;

    @Override
    public List<BookingWithUserModel> getAllWithUser() {
        return bookingConverter.entitiesWithUserToModelsWithUser(bookingRepo.findAllWithUser());
    }

    @Override
    public List<BookingWithUserModel> getAllWithUserByUserPhoneNumber(String phoneNumber) {
        return bookingConverter.entitiesWithUserToModelsWithUser(bookingRepo.findAllWithUserByUserPhoneNumber(phoneNumber));
    }

    @Override
    public List<BookingWithUserModel> getAllWithUserByArrivalDate(LocalDateTime from, LocalDateTime to) {
        return bookingConverter.entitiesWithUserToModelsWithUser(bookingRepo.findAllWithUserByArrival(from, to));
    }

    @Override
    public void create(String phoneNumber, Integer numberOfPeople, LocalDateTime arrival, LocalDateTime departure) throws UserNotFoundException {
        Optional<UserWithLocationModel> user = userService.getWithLocationByPhoneNumber(phoneNumber);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь с номером телефона " + phoneNumber + " не найден");
        }

        bookingRepo.save(BookingEntity.builder()
                .userRef(AggregateReference.to(user.get().id()))
                .numberOfPeople(numberOfPeople)
                .createdAt(LocalDateTime.now())
                .arrival(arrival)
                .isArrived(false)
                .departure(departure)
                .isDeparted(false)
                .isConfirmed(false)
                .isCanceled(false)
                .build());
    }

    @Override
    public void cancel(Long bookingId) {
        bookingRepo.setIsCanceled(bookingId);
    }

    @Override
    public void confirm(Long bookingId) {
        bookingRepo.setIsConfirmed(bookingId);
    }

    @Override
    public void arrive(Long bookingId) {
        bookingRepo.setIsArrived(bookingId);
    }

    @Override
    public void depart(Long bookingId) {
        bookingRepo.setIsDeparted(bookingId);
    }
}
