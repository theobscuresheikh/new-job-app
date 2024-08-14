package com.learn.companyms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public ResponseEntity<List<Company>> getCompanies() {
        return ResponseEntity.ok().body(companyService.getAllCompanies());
    }

    // Add a method to get a company by id
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    // Add a method to create a company
    @PostMapping()
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(companyService.createCompany(company));
    }

    // Add a method to update a company
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        if (companyService.updateCompany(id, company))
            return new ResponseEntity<>("Updated successfully",
                    HttpStatus.NO_CONTENT);
        return ResponseEntity.notFound().build();
    }

    // Add a method to delete a company
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        if (!companyService.deleteCompany(id)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>("Company deleted successfully",
                HttpStatus.NO_CONTENT);
    }

   /* //get reviews for a company
    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> getCompanyReviews(@PathVariable Long id) {
        return new ResponseEntity<>(companyService.getAllReviewsForCompanyId(id), HttpStatus.OK);
    }

    // Add a method to get a review by id for a company by id
    @GetMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = companyService.getReviewByCompanyIdAndReviewId(companyId, reviewId);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // Add a method to create a review for a company
    @PostMapping("/{companyId}/reviews")
    public ResponseEntity<Review> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(companyService.createReview(companyId, review));
    }

    // Add a method to update a review for a company
    @PutMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        if (companyService.updateReview(companyId, reviewId, review))
            return new ResponseEntity<>("Updated successfully",
                    HttpStatus.NO_CONTENT);
        return ResponseEntity.notFound().build();
    }

    // Add a method to delete a review for a company
    @DeleteMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        if (!companyService.deleteReview(companyId, reviewId)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>("Review deleted successfully",
                HttpStatus.NO_CONTENT);
    }*/
}
