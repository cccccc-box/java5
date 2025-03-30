package net.bookstores.assignment.homecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bookstores.assignment.entities.Review;
import net.bookstores.assignment.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Integer id) {
        return reviewService.getReviewById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }
}
