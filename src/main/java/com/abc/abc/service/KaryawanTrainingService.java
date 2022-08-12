package com.abc.abc.service;

import com.abc.abc.dao.KaryawanTrainingRequest;
import com.abc.abc.model.KaryawanTraining;

import java.util.Map;

public interface KaryawanTrainingService {

    public Map insert(KaryawanTrainingRequest obj);

    public Map update(KaryawanTrainingRequest obj);

    public Map delete(Long obj);
}
