package com.tour.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tour.custom_exception.ApiException;
import com.tour.custom_exception.ResourceNotFoundException;
import com.tour.dto.AccommodationResponseDTO;
import com.tour.dto.ApiResponse;
import com.tour.dto.DestinationResponseDTO;
import com.tour.dto.TourDTO;
import com.tour.dto.TourResponseDTO;
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
                // Check if a tour with the same title and start date already exists
                boolean exists = tourRepository.existsByTitleAndStartDate(tourDTO.getTitle(), tourDTO.getStartDate());
                if (exists) {
                    return new ApiResponse(HttpStatus.BAD_REQUEST, "Tour with the same title and start date already exists.");
                }

                // Map TourDTO to Tour entity
                Tour tour = modelMapper.map(tourDTO, Tour.class);

                // Map and set destinations
                List<Destination> destinations = tourDTO.getDestinations().stream()
                        .map(destinationDTO -> {
                            Destination destination = modelMapper.map(destinationDTO, Destination.class);
                            Accommodation accommodation = modelMapper.map(destinationDTO.getAccommodation(), Accommodation.class);
                            destination.setAccommodation(accommodation);
                            return destination;
                        })
                        .collect(Collectors.toList());

                tour.setDestinations(destinations);

                // Save the new tour package
                tourRepository.save(tour);

                return new ApiResponse(HttpStatus.CREATED, "Tour package created successfully.");
            } catch (Exception e) {
                // Handle and throw a custom ApiException with error details
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
        public List<TourResponseDTO> getAllTours() {
            try {
                List<Tour> tours = tourRepository.findAll();

                return tours.stream()
                        .map(tour -> modelMapper.map(tour, TourResponseDTO.class))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                throw new ApiException("Error retrieving all tours: " + e.getMessage());
            }
        }

        @Override
        public List<TourResponseDTO> getTourByTitle(String title) {
            try {
                List<Tour> tours = tourRepository.findByTitle(title);

                if (tours.isEmpty()) {
                    throw new ResourceNotFoundException("No tours found with title: " + title);
                }

                return tours.stream()
                        .map(tour -> modelMapper.map(tour, TourResponseDTO.class))
                        .collect(Collectors.toList());
            } catch (ResourceNotFoundException e) {
                throw e;
            } catch (Exception e) {
                throw new ApiException("Error retrieving tours by title: " + e.getMessage());
            }
        }
        
        @Override
        public TourResponseDTO getTourById(Long tourId) {
            try {
                // Fetch the tour entity by its ID
                Tour tour = tourRepository.findById(tourId)
                        .orElseThrow(() -> new ResourceNotFoundException("Tour not found with ID: " + tourId));

                // Map Tour entity to TourResponseDTO
                TourResponseDTO tourResponseDTO = modelMapper.map(tour, TourResponseDTO.class);
                
                // Map the destinations and their accommodations to the response DTO
                List<DestinationResponseDTO> destinationDTOs = tour.getDestinations().stream()
                        .map(destination -> {
                            // Map Destination to DestinationResponseDTO
                            DestinationResponseDTO destinationDTO = modelMapper.map(destination, DestinationResponseDTO.class);

                            // Map Accommodation to AccommodationResponseDTO
                            if (destination.getAccommodation() != null) {
                                AccommodationResponseDTO accommodationDTO = modelMapper.map(destination.getAccommodation(), AccommodationResponseDTO.class);
                                destinationDTO.setAccommodation(accommodationDTO);
                            }

                            return destinationDTO;
                        })
                        .collect(Collectors.toList());

                // Set the mapped destinations into the TourResponseDTO
                tourResponseDTO.setDestinations(destinationDTOs);

                return tourResponseDTO;
            } catch (ResourceNotFoundException e) {
                throw e;
            } catch (Exception e) {
                throw new ApiException("Error retrieving tour by ID: " + e.getMessage());
            }
        }
}


    
