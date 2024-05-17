package com.matesgate.project.matesgate.controlller;

import com.matesgate.project.matesgate.model.Bookings;
import com.matesgate.project.matesgate.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Bookings createBooking(@RequestBody Bookings booking) {
        return bookingService.saveBooking(booking);
    }

    @GetMapping("/{id}")
    public Bookings getBooking(@PathVariable Integer id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping
    public List<Bookings> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
