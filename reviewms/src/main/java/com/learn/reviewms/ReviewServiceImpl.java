package com.learn.reviewms;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewsRepository reviewsRepository;

    public ReviewServiceImpl(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewsRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReview( Long reviewId) {
        return reviewsRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Review createReview(Long companyId, Review review) {
        if (review.getReview_id() == null) {
            review.setCompanyId(companyId);
            reviewsRepository.save(review);
        }
        return review;
    }

    @Override
    public boolean updateReview(Long reviewId, Review review) {
        Review review1 = getReview(reviewId);
        if (review1 != null) {
            review1.setCompanyId(review.getCompanyId());
            review1.setReview(review.getReview());
            review1.setDescription(review.getDescription());
            review1.setRating(review.getRating());
            reviewsRepository.save(review1);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Optional<Review> review = reviewsRepository.findById(reviewId);
        if (review.isPresent()) {
            reviewsRepository.delete(review.get());
            return true;
        }
        return false;
    }

}


