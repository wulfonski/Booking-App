package ro.sda.booking.core.service;

import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAllRatings();

    Rating getRatingById(Long id);

    void deleteRating(Rating rating);

    void updateRating(Rating rating);

    Rating findByRating(int rating);
}
