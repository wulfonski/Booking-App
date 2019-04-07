package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.entity.Rating;
import ro.sda.booking.core.repository.BookingRepository;
import ro.sda.booking.core.repository.RatingRepository;

import java.util.List;

@Service("ratingService")
@Transactional(rollbackFor = Exception.class)
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    @Transactional
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public void deleteRating(Rating rating) {
        ratingRepository.delete(rating);
    }

    @Override
    public void updateRating(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public Rating findByRating(int rating){
       return ratingRepository.findByRating(rating);
    }
}
