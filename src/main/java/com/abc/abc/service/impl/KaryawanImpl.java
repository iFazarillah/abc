package com.abc.abc.service.impl;


import com.abc.abc.model.Karyawan;
import com.abc.abc.repository.KaryawanRepository;
import com.abc.abc.service.KaryawanService;
import com.abc.abc.utils.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Date;
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
    public Map update(Karyawan kryReq) {
        try {

            if (templateResponse.checkNull(kryReq.getId())) {
                return templateResponse.templateError("Id Barang is required");
            }
            Karyawan checkIdKry = karyawanRepository.getByID(kryReq.getId());
            if (templateResponse.checkNull(checkIdKry)) {
                return templateResponse.templateError("Id Barang Not found");
            }

            checkIdKry.setNama(kryReq.getNama());
            checkIdKry.setDob(kryReq.getDob());
            checkIdKry.setJk(kryReq.getJk());
            checkIdKry.setStatus(kryReq.getStatus());
            checkIdKry.setAlamat(kryReq.getAlamat());
            Karyawan dosave = karyawanRepository.save(checkIdKry);
            return templateResponse.templateSukses(dosave);
        } catch (Exception e) {
            return templateResponse.templateError(e);
        }

    }

    @Override
    public Map delete(Long karyawan) {
        try {
            if (templateResponse.checkNull(karyawan)) {
                return templateResponse.templateError("Id Karyawan is required");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getByID(karyawan);
            if (templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Barang Not found");
            }

            checkIdKaryawan.setDeleted_date(new Date());//
            karyawanRepository.save(checkIdKaryawan);

            return templateResponse.templateSukses("sukses deleted");

        } catch (Exception e) {
            return templateResponse.templateError(e);
        }
    }


    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Karyawan> list = karyawanRepository.getAllData(show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {
            log.error("ada eror di method getAll:" + e);
            return templateResponse.templateError(e);
        }

    }
}
