package com.learn.jobms;


import java.util.List;

public interface JobService {

    Job getJobById(Long id);

    List<Job> getAllJobs();

    Job createJob(Job job);

    boolean deleteJob(Long id);

    boolean updateJob(Long id, Job job);
}
