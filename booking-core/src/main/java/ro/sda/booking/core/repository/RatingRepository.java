package ro.sda.booking.core.repository;

import ro.sda.booking.core.base.EntityRepository;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.entity.Rating;

public interface RatingRepository extends EntityRepository<Rating> {
    public Rating findByRating(int rating);
}
