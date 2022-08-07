package com.abc.abc.sp.model;

import lombok.Data;

import java.util.Date;

@Data
public class KaryawanMybatis {
    Long resid;
    String resname;
    String resjk;
    Date resdob;
    String resalamat;
    String resstatus;
    String reserrordesc;
    Integer reserrorcode;
}
