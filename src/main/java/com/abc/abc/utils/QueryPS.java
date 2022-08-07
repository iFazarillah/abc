package com.abc.abc.utils;

import org.springframework.stereotype.Component;

@Component
public class QueryPS {

        public String saveKaryawanOnlySP = """
                 CREATE OR REPLACE PROCEDURE public.savekaryawanonly(INOUT inama character varying, INOUT ijk character varying, INOUT idob date, INOUT ialamat text, INOUT istatus character varying, INOUT iid bigint, INOUT error_desc character varying, INOUT error_code integer)
                    LANGUAGE plpgsql
                    AS $procedure$
                    begin
                		if inama is null then raise notice 'Nama kosong';
                    error_desc = 'nama wajib diisi';
                    error_code = 404;
                			return;
                		else raise notice 'nama ada';
                    end if;
                                
                    insert into public.karyawan (
                            id,
                            nama,
                            jk,
                            dob,
                            alamat,
                            status)
                                
                    select
                    nextval('Karyawan_id_seq'),
                    inama,
                    ijk,
                    idob,
                    ialamat,
                    istatus
                    returning id into iid;
                    error_desc = 'sukses';
                    error_code = 619;
                                
                                
                    commit;
                                
                                
                    END;
                    $procedure$
                    ;
                    ;
                """;

}
