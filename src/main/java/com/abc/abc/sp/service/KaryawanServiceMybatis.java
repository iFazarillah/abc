package com.abc.abc.sp.service;

import com.abc.abc.sp.model.KaryawanMybatis;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface KaryawanServiceMybatis {


    KaryawanMybatis selectBlog(Integer rqid);

    List<KaryawanMybatis> selectList(String rqname);

    void insertKryOnly(String rqname, String rqjk, Date rqdob, String rqalamat, String rqstatus, int rqid);

    Map savekaryawanwitheror(String inama, Date idob, String ijk, String ialamat, String istatus, Integer iid);

    Map updateKryOnly(String unama, String ujk, Date udob, String ualamat, String ustatus, int uid);

    Map deleteProcedure(int did);

    Map listAllKaryawan(int size, int page);


    //    use xml
//    public Map updateProcedureXML(BarangMybatis item);
//    List<BarangMybatis> listBarangXML(BarangMybatis item);
//    BarangMybatis getByIdBarangXML(Integer id);

//    public Map testPS(KaryawanMybatis item);
}
