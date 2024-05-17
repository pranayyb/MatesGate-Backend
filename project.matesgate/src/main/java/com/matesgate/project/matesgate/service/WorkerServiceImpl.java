package com.matesgate.project.matesgate.service;

import com.matesgate.project.matesgate.model.WorkerProfile;
import com.matesgate.project.matesgate.repository.WorkerRepo;
import com.matesgate.project.matesgate.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepo workerRepository;

    @Override
    public WorkerProfile saveWorkerProfile(WorkerProfile workerProfile) {
        return workerRepository.save(workerProfile);
    }

    @Override
    public WorkerProfile getWorkerProfileById(Integer id) {
        return workerRepository.findById(id).orElse(null);
    }

    @Override
    public List<WorkerProfile> getAllWorkerProfiles() {
        return workerRepository.findAll();
    }

    @Override
    public void deleteWorkerProfile(Integer id) {
        workerRepository.deleteById(id);
    }
}
