package com.learn.reviewms;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    Review getReview( Long reviewId);

    Review createReview(Long companyId, Review review);

    boolean updateReview(Long reviewId, Review review);

    boolean deleteReview( Long reviewId);
}
