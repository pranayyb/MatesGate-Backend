package com.matesgate.project.matesgate.service;

import com.matesgate.project.matesgate.model.Bookings;

import java.util.List;

public interface BookingService {
    Bookings saveBooking(Bookings booking);
    Bookings getBookingById(Integer id);
    List<Bookings> getAllBookings();
    void deleteBooking(Integer id);
}
