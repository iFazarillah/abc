package com.abc.abc.service;

import com.abc.abc.dao.KaryawanTrainingRequest;
import com.abc.abc.model.Rekening;

import java.util.Map;

public interface RekeningService {

    public Map insert(Rekening obj);

    public Map update(Rekening obj);

    public Map delete(Long obj);

    public Map insert(Rekening rekening, Long idkaryawan);

    public Map update(Rekening rekening, Long idkaryawan);

    public Map findById(Long id);


}
