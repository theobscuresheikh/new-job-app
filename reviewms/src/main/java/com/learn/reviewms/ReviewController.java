package com.learn.reviewms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewServiceService;

    public ReviewController(ReviewService reviewServiceService) {
        this.reviewServiceService = reviewServiceService;
    }

    //get reviews for a company
    @GetMapping()
    public ResponseEntity<List<Review>> getCompanyReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewServiceService.getAllReviews(companyId), HttpStatus.OK);
    }

    // Add a method to get a review by id for a company by id
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        Review review = reviewServiceService.getReview(reviewId);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // Add a method to create a review for a company
    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewServiceService.createReview(companyId, review));
    }

    // Add a method to update a review for a company
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        if (reviewServiceService.updateReview(reviewId, review))
            return new ResponseEntity<>("Updated successfully",
                    HttpStatus.NO_CONTENT);
        return ResponseEntity.notFound().build();
    }

    // Add a method to delete a review for a company
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        if (!reviewServiceService.deleteReview(reviewId)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>("Review deleted successfully",
                HttpStatus.NO_CONTENT);
    }
}
