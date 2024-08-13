package com.tour.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tour.custom_exception.ApiException;
import com.tour.custom_exception.ResourceNotFoundException;
import com.tour.dto.ApiResponse;
import com.tour.dto.TourDTO;
import com.tour.entities.Accommodation;
import com.tour.entities.Destination;
import com.tour.entities.Tour;
import com.tour.repository.TourRepository;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse addTour(TourDTO tourDTO) {
        try {
        	System.out.println(tourDTO);
            Tour tour = modelMapper.map(tourDTO, Tour.class);
            System.out.println(tour);
      //      System.out.println(tourDTO.getDestinations()+"*********************");
            List<Destination> destinations = tourDTO.getDestinations().stream()
                    .map(destinationDTO -> {
                        Destination destination = modelMapper.map(destinationDTO, Destination.class);
                        Accommodation accommodation = modelMapper.map(destinationDTO.getAccommodation(), Accommodation.class);
                        destination.setAccommodation(accommodation);
                        return destination;
                    })
                    .collect(Collectors.toList());

            tour.setDestinations(destinations);
            tourRepository.save(tour);

            return new ApiResponse(HttpStatus.CREATED, "Tour package created successfully.");
        } catch (Exception e) {
            throw new ApiException("Error creating tour package: " + e.getMessage());
        }
    }

    @Override
    public ApiResponse updateTour(Long tourId, TourDTO tourDTO) {
        try {
            Tour existingTour = tourRepository.findById(tourId)
                    .orElseThrow(() -> new ResourceNotFoundException("Tour not found with ID: " + tourId));

            existingTour.setTitle(tourDTO.getTitle());
            existingTour.setDescription(tourDTO.getDescription());
            existingTour.setDuration(tourDTO.getDuration());
            existingTour.setStartDate(tourDTO.getStartDate());
            existingTour.setPrice(tourDTO.getPrice());

            List<Destination> updatedDestinations = tourDTO.getDestinations().stream()
                    .map(destinationDTO -> {
                        Destination destination = modelMapper.map(destinationDTO, Destination.class);
                        Accommodation accommodation = modelMapper.map(destinationDTO.getAccommodation(), Accommodation.class);
                        destination.setAccommodation(accommodation);
                        return destination;
                    })
                    .collect(Collectors.toList());

            existingTour.setDestinations(updatedDestinations);
            tourRepository.save(existingTour);

            return new ApiResponse(HttpStatus.OK, "Tour package updated successfully.");
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Error updating tour package: " + e.getMessage());
        }
    }

    @Override
    public ApiResponse deleteTour(Long tourId) {
        try {
            Tour existingTour = tourRepository.findById(tourId)
                    .orElseThrow(() -> new ResourceNotFoundException("Tour not found with ID: " + tourId));

            tourRepository.delete(existingTour);
            return new ApiResponse(HttpStatus.OK, "Tour package deleted successfully.");
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Error deleting tour package: " + e.getMessage());
        }
    }

    @Override
    public TourDTO getTourByTitle(String title) {
        try {
            Tour tour = tourRepository.findByTitle(title)
                    .orElseThrow(() -> new ResourceNotFoundException("Tour not found with title: " + title));

            return modelMapper.map(tour, TourDTO.class);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Error retrieving tour by title: " + e.getMessage());
        }
    }

    @Override
    public List<TourDTO> getAllTours() {
        try {
            List<Tour> tours = tourRepository.findAll();

            return tours.stream()
                    .map(tour -> modelMapper.map(tour, TourDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ApiException("Error retrieving all tours: " + e.getMessage());
        }
    }
}
