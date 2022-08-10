package com.abc.abc.utils;

import com.abc.abc.model.Karyawan;
import com.abc.abc.sp.model.KaryawanMybatis;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TemplateResponse {

    public Map templateSukses (Object object){
        Map map = new HashMap();
        map.put("data", object);
        map.put("message", "Sukses");
        map.put("status", "200");
        return map;
    }

    public Map templateSukses(Object object, String Message, String status){
        Map map = new HashMap();
        map.put("data", object);
        map.put("message", "Sukses");
        map.put("status", "200");
        return map;
    }

    public Map templateError(Object object){
        Map map = new HashMap();
        map.put("message", object);
        map.put("status", "404");
        return map;
    }

    public Map notFound(Object object){
        Map map = new HashMap();
        map.put("message", object);
        map.put("status", "404");
        return map;
    }

    public boolean checkNull(Object obj) {
        return obj == null;

    }

    public Karyawan convertToKaryawan(KaryawanMybatis obj){
        Karyawan objKaryawan = new Karyawan();
        objKaryawan.setId(obj.getResid());
        objKaryawan.setNama(obj.getResnama());
        objKaryawan.setJk(obj.getResjk());
        objKaryawan.setDob(obj.getResdob());
        objKaryawan.setStatus(obj.getResstatus());
        objKaryawan.setAlamat(obj.getResalamat());
        objKaryawan.setCreated_date(obj.getRescreated_date());
        objKaryawan.setUpdated_date(obj.getResupdated_date());
        objKaryawan.setDeleted_date(obj.getResdeleted_date());
        return  objKaryawan;
    }

    public List<Karyawan> convertToKaryawan(List<KaryawanMybatis> list){
        List<Karyawan> listKaryawan=  new ArrayList<>();
        for(KaryawanMybatis obj : list){
            Karyawan objKaryawan = new Karyawan();
            objKaryawan.setId(obj.getResid());
            objKaryawan.setJk(obj.getResjk());
            objKaryawan.setDob(obj.getResdob());
            objKaryawan.setStatus(obj.getResstatus());
            objKaryawan.setAlamat(obj.getResalamat());
            objKaryawan.setNama(obj.getResnama());
            objKaryawan.setCreated_date(obj.getRescreated_date());
            objKaryawan.setUpdated_date(obj.getResupdated_date());
            objKaryawan.setDeleted_date(obj.getResdeleted_date());
            listKaryawan.add(objKaryawan);
        }
        return  listKaryawan;
    }
}
