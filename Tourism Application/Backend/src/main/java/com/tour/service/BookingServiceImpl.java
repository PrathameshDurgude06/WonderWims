package com.tour.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tour.custom_exception.ResourceNotFoundException;
import com.tour.dto.ApiResponse;
import com.tour.dto.BookingDTO;
import com.tour.entities.Booking;
import com.tour.entities.Tour;
import com.tour.entities.User;
import com.tour.repository.BookingRepository;
import com.tour.repository.TourRepository;
import com.tour.repository.UserRepository;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ApiResponse bookTourPackage(BookingDTO bookingDTO) {
        Tour tour = tourRepository.findById(bookingDTO.getTourId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Tour ID"));

        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid User ID"));

        Booking booking = mapper.map(bookingDTO, Booking.class);
        booking.setUser(user);
        booking.setTour(tour);

        bookingRepository.save(booking);

        return new ApiResponse(HttpStatus.OK, "Booking done successfully");
    }

    @Override
    public BookingDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Booking ID"));
        
        return mapper.map(booking, BookingDTO.class);
    }

    @Override
    public List<BookingDTO> getCustomerBookings(Long userId) {
    	User u = this.userRepository.findById(userId).orElse(null);
         return this.bookingRepository.findByUser(u).stream().map(booking-> new BookingDTO(booking.getBookingId(), booking.getStatus(), booking.getBookingDate(), booking.getTotalCost(),booking.getUser().getUserId(), booking.getTour().getTourId())).collect(Collectors.toList());
   
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> mapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ApiResponse deleteBookingByCustomer(Long bookingId, Long userId) {
    	 if (!bookingRepository.existsById(bookingId)) {
    	        throw new ResourceNotFoundException("Invalid Booking ID");
    	 }
    	 
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Booking ID"));

        if (!booking.getUser().getUserId().equals(userId)) {
            throw new ResourceNotFoundException("Booking does not belong to the user");
        }

        bookingRepository.deleteById(bookingId);
        return new ApiResponse(HttpStatus.OK, "Booking deleted successfully");
    }
    
    @Override
    public List<BookingDTO> searchBookings(String status, LocalDate startDate) {
        List<Booking> bookings = bookingRepository.findByStatusAndBookingDate(status, startDate);
        return bookings.stream().map(booking -> mapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
    }
    
    @Override
    public ApiResponse updateBooking(Long bookingId, BookingDTO bookingDTO) {
    	if (!bookingRepository.existsById(bookingId)) {
            throw new ResourceNotFoundException("Invalid Booking ID");
        }
    	
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Booking ID"));
        existingBooking.setStatus(bookingDTO.getStatus());
        existingBooking.setBookingDate(bookingDTO.getBookingDate());
        existingBooking.setTotalCost(bookingDTO.getTotalCost());
        
        bookingRepository.save(existingBooking);
        return new ApiResponse(HttpStatus.OK, "Booking updated successfully");
    }
}
