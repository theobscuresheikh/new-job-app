package com.learn.jobms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping()
    public ResponseEntity<List<Job>> getJobs() {
        return ResponseEntity.ok().body(jobService.getAllJobs());
    }

    @PostMapping()
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(job));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        if (!jobService.deleteJob(id)) {
            return ResponseEntity.notFound().build();
        }
//        return ResponseEntity.noContent().build();
        return new ResponseEntity<>("Job deleted successfully",
                HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) {
        if (jobService.updateJob(id, job))
            return new ResponseEntity<>("Updated successfully", HttpStatus.NO_CONTENT);
        return ResponseEntity.notFound().build();
    }
}
