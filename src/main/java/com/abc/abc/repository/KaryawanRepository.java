package com.abc.abc.repository;

import com.abc.abc.model.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanRepository extends PagingAndSortingRepository<Karyawan, Long> {
    @Query("SELECT c from Karyawan c")// nama class
    Page<Karyawan> getAllData(Pageable pageable);

    @Query("SELECT c from Karyawan c WHERE c.id = :idkaryawan")
    Karyawan getByID(@Param("idkaryawan") Long idbebas);



}
