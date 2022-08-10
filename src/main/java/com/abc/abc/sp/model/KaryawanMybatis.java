package com.abc.abc.sp.model;

import lombok.Data;

import java.util.Date;

@Data
public class KaryawanMybatis {
    Long resid;
    String resnama;
    String resjk;
    Date resdob;
    String resalamat;
    String resstatus;
    Date rescreated_date;
    Date resupdated_date;
    Date resdeleted_date;
    String reserrordesc;
    Integer reserrorcode;
}
