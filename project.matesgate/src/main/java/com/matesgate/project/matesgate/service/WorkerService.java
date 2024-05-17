package com.matesgate.project.matesgate.service;

import com.matesgate.project.matesgate.model.WorkerProfile;

import java.util.List;

public interface WorkerService {
    WorkerProfile saveWorkerProfile(WorkerProfile workerProfile);
    WorkerProfile getWorkerProfileById(Integer id);
    List<WorkerProfile> getAllWorkerProfiles();
    void deleteWorkerProfile(Integer id);
}
