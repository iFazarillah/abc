package com.abc.abc.sp.repository;

import com.abc.abc.sp.model.KaryawanMybatis;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface KaryawanRepoMybatis {

    @Select("SELECT resid, resnama ,resjk, resdob, resalamat, resstatus, rescreated_date, resupdated_date, resdeleted_date FROM public.getkaryawanbyid(#{rqId});")
    KaryawanMybatis selectBlog(int rqid);

    @Select("select resid, resnama ,resjk, resdob, resalamat, resstatus, rescreated_date, resupdated_date, resdeleted_date from public.getkaryawanbyname(#{rqNama});")
    List<KaryawanMybatis> selectList(String rqnama);

    @Insert(value = "call savekaryawanonly(#{inama,mode=INOUT}," +
            "#{ijk,mode=INOUT}," +
            "#{idob,mode=INOUT}," +
            "#{ialamat,mode=INOUT}," +
            "#{istatus,mode=INOUT}," +
            "#{iid,mode=INOUT}," +
            "#{error_desc,mode=INOUT}," +
            "#{error_code,mode=INOUT});")
    void insertProcedure(String inama, String ijk, Date idob, String ialamat, String istatus, int iid);


    @Update(value = "call savekaryawanonly(#{inama,mode=INOUT,jdbcType=VARCHAR}," +
            "#{ijk,mode=INOUT,jdbcType=VARCHAR}," +
            "#{idob,mode=INOUT,jdbcType=DATE}," +
            "#{ialamat,mode=INOUT,jdbcType=LONGVARCHAR}," +
            "#{istatus,mode=INOUT,jdbcType=VARCHAR}," +
            "#{iid,mode=INOUT,jdbcType=INTEGER}," +
            "#{error_desc,mode=INOUT,jdbcType=VARCHAR}," +
            "#{error_code,mode=INOUT,jdbcType=INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void savekaryawanwitheror(Map<String,Object> map);

    @Update(value = "call public.updatekaryawanonly(#{unama,mode=INOUT},#{ujk,mode=INOUT},#{udob,mode=INOUT},#{ualamat,mode=INOUT},#{ustatus,mode=INOUT},#{uid,mode=INOUT});")
    void updateKryOnly(String unama, String ujk, Date udob, String ualamat, String ustatus, int uid);

    @Update("call deletebarang(#{did,mode=IN})")
    void deleteProcedure(int did);


}