package com.abc.abc.service;

import com.abc.abc.model.Karyawan;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface KaryawanService {

    public Map insert(Karyawan karyawan);

    public Map update(Karyawan karyawan);

    public Map delete(Long karyawan);

    public Map getAll(int size, int page);

}

