package com.abc.abc.service;

import com.abc.abc.model.Training;

import java.util.Map;

public interface TrainingService {

    public Map insert(Training obj);

    public Map update(Training obj);

    public Map delete(Long idTraining);
}
