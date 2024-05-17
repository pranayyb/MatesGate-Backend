package com.matesgate.project.matesgate.service;

import com.matesgate.project.matesgate.model.Bookings;
import com.matesgate.project.matesgate.repository.BookingRepo;
import com.matesgate.project.matesgate.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepository;

    @Override
    public Bookings saveBooking(Bookings booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Bookings getBookingById(Integer id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Bookings> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }
}
