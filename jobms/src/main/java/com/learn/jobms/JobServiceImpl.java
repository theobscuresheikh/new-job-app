package com.learn.jobms;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job getJobById(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        return jobOptional.orElse(null);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        jobRepository.save(job);
        return job;
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        return jobRepository.findById(id)
                .map(existingJob -> {
                    existingJob.setTitle(updatedJob.getTitle());
                    existingJob.setDescription(updatedJob.getDescription());
                    existingJob.setLocation(updatedJob.getLocation());
                    existingJob.setMinSalary(updatedJob.getMinSalary());
                    existingJob.setMaxSalary(updatedJob.getMaxSalary());
                    jobRepository.save(existingJob);
                    return true;
                })
                .orElse(false);
    }
}
