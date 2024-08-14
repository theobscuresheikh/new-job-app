package com.learn.reviewms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long id);
}
