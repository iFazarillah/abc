package com.abc.abc.repository;


import com.abc.abc.model.Karyawan;
import com.abc.abc.model.Rekening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeningRepository extends PagingAndSortingRepository<Rekening, Long> {

    @Query("select c from Rekening c WHERE c.id = :id")
    public Rekening getbyID(@Param("id") Long id);

//    @Query("select k.nama, r  from Karyawan k inner join u.rekening r WHERE r.karyawan_id = :karyawan_id")
//    public Rekening getByIdKaryawan(@Param("karyawan_id") Long idkaryawan);

//    public Page<Rekening> getByIdKaryawan(Long idkaryawan);
}
