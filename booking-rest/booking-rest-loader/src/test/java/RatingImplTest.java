import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.entity.Rating;
import ro.sda.booking.core.service.ClientService;
import ro.sda.booking.core.service.PropertyService;
import ro.sda.booking.core.service.RatingService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional

public class RatingImplTest {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PropertyService propertyService;

    @Test
    @Rollback(false)
    public void testCreateRating() {
        Rating rating = new Rating();
        rating.setClient(clientService.getClientById(5L));
        rating.setCommment("Excellent");
        rating.setProperty(propertyService.getPropertyById(4L));
        rating.setRating(5);
        ratingService.createRating(rating);
        Assert.assertEquals("Excellent", rating.getCommment());
        Assert.assertEquals(5, rating.getRating());

    }

    @Test
    @Rollback(false)
    @Transactional
    public void testGetAllRatings() {
        Rating rating = new Rating();
        rating.setClient(clientService.getClientById(4L));
        rating.setCommment("Good");
        rating.setProperty(propertyService.getPropertyById(1L));
        rating.setRating(3);
        ratingService.createRating(rating);
        Rating rating1 = new Rating();
        rating1.setClient(clientService.getClientById(5L));
        rating1.setCommment("Bad");
        rating1.setProperty(propertyService.getPropertyById(4L));
        rating1.setRating(1);
        ratingService.createRating(rating1);
        List<Rating> ratings = ratingService.getAllRatings();
        Assert.assertEquals(3, ratings.size());
    }

    @Test
    public void testGetRatingById() {
        Rating rating = ratingService.getRatingById(2L);
        Long ratingId = rating.getId();
        String ratingComment = rating.getCommment();
        Assert.assertEquals("Good", ratingComment);
        Assert.assertEquals(new Long(2), ratingId);
    }

    @Test
    @Rollback(false)
    public void testDeleteRating() {
        List<Rating> allRatings = ratingService.getAllRatings();
        int size1 = allRatings.size();
        Rating rating = ratingService.getRatingById(3L);
        ratingService.deleteRating(rating);
        List<Rating> allRatings2 = ratingService.getAllRatings();
        int size2 = allRatings2.size();
        Assert.assertEquals(size1 - 1, size2);
    }

    @Test
    @Rollback(false)
    public void testUpdateRating() {
        Rating rating = ratingService.getRatingById(1L);
        rating.setCommment("Bad");
        String ratingComment = rating.getCommment();
        rating.setRating(2);
        int ratingRating = rating.getRating();
        ratingService.updateRating(rating);
        Assert.assertEquals("Bad", ratingComment);
        Assert.assertEquals(2, ratingRating);

    }

    @Test
    public void testFindByRating() {
        Rating rating = ratingService.findByRating(2);
        int ratingRating = rating.getRating();
        Long ratingId = rating.getId();
        Assert.assertEquals(new Long(1), ratingId);
        Assert.assertEquals(2, ratingRating);
    }
}
