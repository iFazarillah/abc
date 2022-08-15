package com.abc.abc.model;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "karyawan")
@Where(clause = "deleted_date is null")
public class Karyawan extends AbstractDate implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false, length = 45)
    private String nama;

    @Column(name = "jk", length = 10)
    private String jk;

    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyMMdd")
    private Date dob;

    @Column(name = "alamat", columnDefinition = "TEXT")
    private String alamat;

    @Column(name = "status", length = 15)
    private String status;

    @OneToOne(mappedBy = "karyawan")
    @JsonIgnoreProperties("karyawan")
    private DetailKaryawan detailKaryawan;

//    @OneToMany(mappedBy = "karyawan")
//    @JsonIgnoreProperties("karyawan")
//    List<KaryawanTraining> karyawanTrainings;

//	@JsonIgnore
//	@OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Rekening> rekenings;


}
