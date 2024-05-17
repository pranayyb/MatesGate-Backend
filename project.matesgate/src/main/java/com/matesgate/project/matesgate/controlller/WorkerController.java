package com.matesgate.project.matesgate.controlller;

import com.matesgate.project.matesgate.model.WorkerProfile;
import com.matesgate.project.matesgate.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping
    public WorkerProfile createWorkerProfile(@RequestBody WorkerProfile workerProfile) {
        return workerService.saveWorkerProfile(workerProfile);
    }

    @GetMapping("/{id}")
    public WorkerProfile getWorkerProfile(@PathVariable Integer id) {
        return workerService.getWorkerProfileById(id);
    }

    @GetMapping
    public List<WorkerProfile> getAllWorkerProfiles() {
        return workerService.getAllWorkerProfiles();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkerProfile(@PathVariable Integer id) {
        workerService.deleteWorkerProfile(id);
        return ResponseEntity.noContent().build();
    }
}
