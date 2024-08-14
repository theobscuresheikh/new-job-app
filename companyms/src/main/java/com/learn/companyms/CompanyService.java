package com.learn.companyms;

import java.util.List;

public interface CompanyService {
    Company getCompanyById(Long id);

    List<Company> getAllCompanies();

    Company createCompany(Company company);

    boolean deleteCompany(Long id);

    boolean updateCompany(Long id, Company company);

    /*List<Review> getAllReviewsForCompanyId(Long id);

    Review getReviewByCompanyIdAndReviewId(Long companyId, Long reviewId);

    Review createReview(Long companyId, Review review);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReview(Long companyId, Long reviewId);*/
}
