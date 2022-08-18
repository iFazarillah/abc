package com.abc.abc.utils;

import org.springframework.stereotype.Component;

@Component
public class QueryPS {

    public String saveKaryawanOnlySP = """
             CREATE OR REPLACE PROCEDURE public.savekaryawanonly(
             INOUT inama character varying,
             INOUT ijk character varying,
             INOUT idob date,
             INOUT ialamat text,
             INOUT istatus character varying,
             INOUT iid integer,
             INOUT error_desc character varying,
             INOUT error_code integer)
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
                        status,
                        created_date)
                            
                select
                nextval('Karyawan_id_seq'),
                inama,
                ijk,
                idob,
                ialamat,
                istatus,
                now()
                returning id into iid;
                error_desc = 'sukses';
                error_code = 619;
                            
                            
                commit;
                            
                            
                END;
                $procedure$
                ;
                ;
            """;

    public String updateKryOnly = """
            CREATE OR REPLACE PROCEDURE public.updatekaryawanonly(INOUT unama character varying, INOUT ujk character varying, INOUT udob date, INOUT ualamat text, INOUT ustatus character varying, INOUT uid integer)
             LANGUAGE plpgsql
            AS $procedure$
            	BEGIN
            		if not exists (select id from karyawan k where id = uid) then\s
            			raise notice 'id tidak ada';
            			return;
            		else
            			raise notice 'id ada';
            		end if;
            		
            		update public.karyawan\s
            		set\s
            			nama = unama,
            			jk = ujk,
            			dob = udob,
            			alamat = ualamat,
            			status = ustatus,
            			updated_date = now()
            		where id = uid returning id into uid;
            		
            		commit;
            	
            	END;

            $procedure$
            ;
                            
            """;

    public String deleteKryOnly = """
            CREATE OR REPLACE PROCEDURE public.softdeletekaryawan(IN did bigint)
             LANGUAGE plpgsql
            AS $procedure$
            	begin
             		if not exists (select id from rekening where id = did)\s
             			then raise notice 'id tidak ada';
             		else
             			raise notice 'id ada';
             		end if;
             
             		update public.karyawan \s
             		set deleted_date = now()
             		where id = did;\s
             		commit;
             
             	END;
            $procedure$
            ;
                            
            """;

    public String getKryByNama = """
            CREATE OR REPLACE FUNCTION public.getkaryawanbyname(rqnama character varying)
             RETURNS TABLE(resid integer, resnama character varying, resjk character varying, resdob date, resalamat character varying, resstatus character varying,rescreated_date date, resupdated_date date, resdeleted_date date)
             LANGUAGE plpgsql
            AS $function$
            	
            		DECLARE\s
                	var_r record;
            	BEGIN
            	    FOR var_r IN(SELECT\s
            	                id,
            	                nama,
            	                jk,
            	                dob,
            	                alamat,
            	                status,
            	                created_date,
            	                updated_date,
            	                deleted_date
            	                FROM karyawan
            	                WHERE nama ilike rqNama) \s
            		    LOOP
            		        resid :=  var_r.id  ;\s
            		        resnama := var_r.nama ;\s
            		        resjk :=  var_r.jk ;\s
            		        resdob := var_r.dob  ;\s
            		        resalamat :=  var_r.alamat  ;
            		       	resstatus :=  var_r.status  ;
            		       	rescreated_date := var_r.created_date;
            		       	resupdated_date := var_r.updated_date;
            		       	resdeleted_date := var_r.deleted_date;
            		        RETURN NEXT;
            		    END LOOP;
            end;
                    
            $function$
            ;
                    
            """;

    public String getKryById = """
            CREATE OR REPLACE FUNCTION public.getkaryawanbyid(rqid bigint)
             RETURNS TABLE(resid bigint, resnama character varying, resjk character varying, resdob date, resalamat text, resstatus character varying)
             LANGUAGE plpgsql
            AS $function$
            	BEGIN
              RETURN QUERY
                select k.id, k.nama, k.jk, k.dob, k.alamat, k.status
                FROM public.karyawan AS k
                WHERE id = rqid;
            	END;
            $function$
            ;
                    
            """;

}
