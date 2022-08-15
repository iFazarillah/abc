package com.abc.abc.sp.service.impl;

import com.abc.abc.model.Karyawan;
import com.abc.abc.sp.model.KaryawanMybatis;
import com.abc.abc.sp.repository.KaryawanRepoMybatis;
import com.abc.abc.sp.service.KaryawanServiceMybatis;
import com.abc.abc.utils.QueryPS;
import com.abc.abc.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KaryawanServiceImplMybatis implements KaryawanServiceMybatis {

    @Autowired
    KaryawanRepoMybatis karyawanRepoMybatis;
    @Autowired
    public TemplateResponse templateResponse;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    QueryPS queryPS;

    @Override
    public KaryawanMybatis selectBlog(Integer rqid) {
        return karyawanRepoMybatis.selectBlog(rqid);
    }

    @Override
    public List<KaryawanMybatis> selectList(String rqname) {
        return karyawanRepoMybatis.selectList(rqname);
    }

    @Override
    public void insertKryOnly(String rqname, String rqjk, Date rqdob, String rqalamat, String rqstatus, int rqid) {
        karyawanRepoMybatis.insertProcedure(rqname, rqjk, rqdob, rqalamat, rqstatus, rqid);
    }

    @Override
    public Map savekaryawanwitheror(String inama, Date idob, String ijk, String ialamat, String istatus, Integer iid) {
        Map<String, Object> map = new HashMap<>();
        map.put("inama", inama);
        map.put("idob", idob);
        map.put("ijk", ijk);
        map.put("ialamat", ialamat);
        map.put("istatus", istatus);
        map.put("iid", (iid == null ? 0 : iid));
        map.put("errorDesc", null);
        map.put("errorCode", null);
        karyawanRepoMybatis.savekaryawanwitheror(map);

        System.out.println("resid =" + (Integer) map.get("iid"));
        System.out.println("resnama =" + (String) map.get("inama"));
        System.out.println("resdob =" + (Date) map.get("idob"));
        System.out.println("resjk =" + (String) map.get("ijk"));
        System.out.println("resalamat =" + (String) map.get("ialamat"));
        System.out.println("resstatus =" + (String) map.get("istatus"));
        System.out.println("reserrordesc =" + (String) map.get("errorDesc"));
        System.out.println("reserrorcode =" + (String) map.get("errorCode"));

        Karyawan objKaryawan = new Karyawan();
        objKaryawan.setId(Long.parseLong(String.valueOf((Integer) map.get("iid"))));
        objKaryawan.setNama((String) map.get("inama"));
        objKaryawan.setDob((Date) map.get("idob"));
        objKaryawan.setJk((String) map.get("ijk"));
        objKaryawan.setAlamat((String) map.get("ialamat"));
        objKaryawan.setStatus((String) map.get("istatus"));
        return templateResponse.templateSukses(objKaryawan, (String) map.get("errorDesc"), String.valueOf((Integer) map.get("errorCode")));
    }

    @Override
    public Map updateKryOnly(String unama, String ujk, Date udob, String ualamat, String ustatus, int uid) {
        karyawanRepoMybatis.updateKryOnly(unama, ujk, udob, ualamat, ustatus, uid);
        return null;

    }

    @Override
    public Map deleteProcedure(int did) {
        karyawanRepoMybatis.deleteProcedure(did);
        return null;
    }


//    @Autowired
//    public BarangRepoMybatisXML barangRepoMybatisXML;


}
