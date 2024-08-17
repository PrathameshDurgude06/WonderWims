package com.tour.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tour.custom_exception.ApiException;
import com.tour.custom_exception.ResourceNotFoundException;
import com.tour.dto.AccommodationDTO;
import com.tour.dto.AccommodationResponseDTO;
import com.tour.dto.ApiResponse;
import com.tour.dto.DestinationResponseDTO;
import com.tour.dto.TourDTO;
import com.tour.dto.TourResponseDTO;
import com.tour.entities.Accommodation;
import com.tour.entities.Destination;
import com.tour.entities.Tour;
import com.tour.repository.AccommodationRepository;
import com.tour.repository.DestinationRepository;
import com.tour.repository.TourRepository;

    @Service
    @Transactional
    public class TourServiceImpl implements TourService {

        @Autowired
        private TourRepository tourRepository;
        @Autowired
        private AccommodationRepository accommodationRepository;
        
        @Autowired
        private DestinationRepository destinationRepository;

        @Autowired
        private ModelMapper modelMapper;

        @Override
        public ApiResponse addTour(TourDTO tourDTO) {
            try {
                boolean exists = tourRepository.existsByTitleAndStartDate(tourDTO.getTitle(), tourDTO.getStartDate());
                if (exists) {
                    return new ApiResponse(HttpStatus.BAD_REQUEST, "Tour with the same title and start date already exists.");
                }

                Tour tour = new Tour();
                tour.setTitle(tourDTO.getTitle());
                tour.setDescription(tourDTO.getDescription());
                tour.setImage(tourDTO.getImageLink());
                tour.setDuration(tourDTO.getDuration());
                tour.setStartDate(tourDTO.getStartDate());
                tour.setPrice(tourDTO.getPrice());

                List<Destination> destinations = tourDTO.getDestinations().stream()
                        .map(destinationDTO -> {
                            Destination destination = new Destination();
                            destination.setDestId(destinationDTO.getDestId());
                            destination.setDestName(destinationDTO.getDestName());
                            destination.setState(destinationDTO.getState());
                            destination.setDescription(destinationDTO.getDescription());
                            
                            Accommodation accommodation = new Accommodation();
                            if (destinationDTO.getAccommodation() != null) {
                                AccommodationDTO accommodationDTO = destinationDTO.getAccommodation();
                                accommodation.setAccoId(accommodationDTO.getAccoId());
                                accommodation.setName(accommodationDTO.getName());
                                accommodation.setType(accommodationDTO.getType());
                                accommodation.setLocation(accommodationDTO.getLocation());
                                accommodation.setDetails(accommodationDTO.getDetails());
                                accommodation.setCheckIn(accommodationDTO.getCheckIn());
                                accommodation.setCheckOut(accommodationDTO.getCheckOut());
                            }
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
        @Transactional
        public ApiResponse deleteTour(Long tourId) {
            try {
                Tour existingTour = tourRepository.findById(tourId)
                        .orElseThrow(() -> new ResourceNotFoundException("Tour not found with ID: " + tourId));
                
                // Check for constraints or references in other parts of the system
                List<Long> accommodationIds = existingTour.getDestinations().stream()
                        .map(destination -> destination.getAccommodation().getAccoId())
                        .collect(Collectors.toList());
                
                List<Long> destinationIds = existingTour.getDestinations().stream()
                        .map(Destination::getDestId)
                        .collect(Collectors.toList());
                
                System.out.println("accommodationIds :- "+accommodationIds+" destinationIds:- "+destinationIds+" tourId:-"+tourId);
                // Delete destinations next
                destinationRepository.deleteDestinationsByIds(destinationIds);
                
                tourRepository.deleteTourById(tourId);
                // Delete accommodations first
                accommodationRepository.deleteAccommodationsByIds(accommodationIds);
                
                // Finally, delete the tour
                

                return new ApiResponse(HttpStatus.OK, "Tour package deleted successfully.");
            } catch (ResourceNotFoundException e) {
                throw e;
            } catch (ConstraintViolationException e) {
                throw new ApiException("Error deleting tour package due to a constraint violation: " + e.getMessage());
            } catch (Exception e) {
                throw new ApiException("Error deleting tour package: " + e.getMessage());
            }
        }


        @Override
        public List<TourResponseDTO> getAllTours() {
            try {
                List<Tour> tours = tourRepository.findAllWithDestinations();

                List<TourResponseDTO> tourResponseDTOs = new ArrayList<>();

                for (Tour tour : tours) {
                    if (tour != null) {
                        TourResponseDTO tourDTO = new TourResponseDTO();
                        tourDTO.setTourId(tour.getTourId());
                        tourDTO.setTitle(tour.getTitle());
                        tourDTO.setDescription(tour.getDescription());
                        tourDTO.setImageLink(tour.getImage());
                        tourDTO.setDuration(tour.getDuration());
                        tourDTO.setStartDate(tour.getStartDate());
                        tourDTO.setPrice(tour.getPrice());

                        List<DestinationResponseDTO> destinationDTOs = new ArrayList<>();
                        if (tour.getDestinations() != null) {
                            for (Destination destination : tour.getDestinations()) {
                                if (destination != null) {
                                    DestinationResponseDTO destinationDTO = new DestinationResponseDTO();
                                    destinationDTO.setDestName(destination.getDestName());
                                    destinationDTO.setState(destination.getState());
                                    destinationDTO.setDescription(destination.getDescription());
                                    destinationDTOs.add(destinationDTO);
                                }
                            }
                        }

                        tourDTO.setDestinations(destinationDTOs);
                        tourResponseDTOs.add(tourDTO);
                    }
                }

                return tourResponseDTOs;
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

                List<TourResponseDTO> tourResponseDTOs = new ArrayList<>();

                for (Tour tour : tours) {
                    if (tour != null) {
                        TourResponseDTO tourDTO = new TourResponseDTO();
                        tourDTO.setTitle(tour.getTitle());
                        tourDTO.setDescription(tour.getDescription());
                        tourDTO.setImageLink(tour.getImage());
                        tourDTO.setDuration(tour.getDuration());
                        tourDTO.setStartDate(tour.getStartDate());
                        tourDTO.setPrice(tour.getPrice());

                        List<DestinationResponseDTO> destinationDTOs = new ArrayList<>();
                        if (tour.getDestinations() != null) {
                            for (Destination destination : tour.getDestinations()) {
                                if (destination != null) {
                                    DestinationResponseDTO destinationDTO = new DestinationResponseDTO();
                                    destinationDTO.setDestName(destination.getDestName());
                                    destinationDTO.setState(destination.getState());
                                    destinationDTO.setDescription(destination.getDescription());
                                    // Accommodation details are not included
                                    destinationDTOs.add(destinationDTO);
                                }
                            }
                        }

                        tourDTO.setDestinations(destinationDTOs);
                        tourResponseDTOs.add(tourDTO);
                    }
                }

                return tourResponseDTOs;
            } catch (ResourceNotFoundException e) {
                throw e;
            } catch (Exception e) {
                throw new ApiException("Error retrieving tours by title: " + e.getMessage());
            }
        }

        
        @Override
        public TourResponseDTO getTourById(Long tourId) {
            try {
                Tour tour = tourRepository.findById(tourId)
                        .orElseThrow(() -> new ResourceNotFoundException("Tour not found with ID: " + tourId));

                TourResponseDTO tourResponseDTO = new TourResponseDTO();
                tourResponseDTO.setTitle(tour.getTitle());
                tourResponseDTO.setDescription(tour.getDescription());
                tourResponseDTO.setImageLink(tour.getImage());
                tourResponseDTO.setDuration(tour.getDuration());
                tourResponseDTO.setStartDate(tour.getStartDate());
                tourResponseDTO.setPrice(tour.getPrice());

                List<DestinationResponseDTO> destinationDTOs = new ArrayList<>();
                for (Destination destination : tour.getDestinations()) {
                    DestinationResponseDTO destinationDTO = new DestinationResponseDTO();
                    destinationDTO.setDestName(destination.getDestName());
                    destinationDTO.setState(destination.getState());
                    destinationDTO.setDescription(destination.getDescription());

                    if (destination.getAccommodation() != null) {
                        AccommodationResponseDTO accommodationDTO = new AccommodationResponseDTO();
                        accommodationDTO.setName(destination.getAccommodation().getName());
                        accommodationDTO.setType(destination.getAccommodation().getType());
                        accommodationDTO.setLocation(destination.getAccommodation().getLocation());
                        accommodationDTO.setDetails(destination.getAccommodation().getDetails());
                        accommodationDTO.setCheckIn(destination.getAccommodation().getCheckIn());
                        accommodationDTO.setCheckOut(destination.getAccommodation().getCheckOut());
                        destinationDTO.setAccommodation(accommodationDTO);
                    }

                    destinationDTOs.add(destinationDTO);
                }

                
                tourResponseDTO.setDestinations(destinationDTOs);

                return tourResponseDTO;
            } catch (ResourceNotFoundException e) {
                throw e;
            } catch (Exception e) {
                throw new ApiException("Error retrieving tour by ID: " + e.getMessage());
            }
        }

}


    
