package com.abc.abc.service.impl;


import com.abc.abc.model.Karyawan;
import com.abc.abc.repository.KaryawanRepository;
import com.abc.abc.service.KaryawanService;
import com.abc.abc.utils.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class KaryawanImpl implements KaryawanService {

    @Autowired
    public KaryawanRepository karyawanRepository;

    public static final Logger log = LoggerFactory.getLogger(KaryawanImpl.class);

    @Autowired
    public TemplateResponse templateResponse;

    @Override
    public Map insert(Karyawan karyawan) {

        try {
            if (templateResponse.checkNull(karyawan.getNama())) {
                return templateResponse.templateError("Nama is required");
            }
            Karyawan kryObj = karyawanRepository.save(karyawan);
            log.info("{}", "Sukses");
            return templateResponse.templateSukses(kryObj);
        } catch (Exception e) {
            log.info("{}", "Eror" + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map update(Karyawan karyawan) {
        return null;
    }

    @Override
    public Map delete(Long karyawan) {
        return null;
    }

    @Override
    public Map getAll(int size, int page) {
        return null;
    }
}
