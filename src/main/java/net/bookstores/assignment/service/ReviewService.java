package net.bookstores.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bookstores.assignment.dao.ReviewDao;
import net.bookstores.assignment.entities.Review;

@Service
public class ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    // Lưu đánh giá mới
    public Review saveReview(Review review) {
        return reviewDao.save(review);
    }

    // Lấy tất cả đánh giá
    public List<Review> getAllReviews() {
        return reviewDao.findAll();
    }

    // Tìm kiếm đánh giá theo ID
    public Optional<Review> getReviewById(Integer id) {
        return reviewDao.findById(id);
    }

    // Xóa đánh giá
    public void deleteReview(Integer id) {
        reviewDao.deleteById(id);
    }
}