package com.abc.abc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "rekening")
@Where(clause = "deleted_date is null")
public class Rekening extends AbstractDate implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", length = 45)
    private String nama;

    @Column(name = "jenis", length = 20)
    private String jenis;

    @Column(name = "nomor", length = 10)
    private String nomor;

    @ManyToOne(targetEntity = Karyawan.class)
    @JsonIgnoreProperties("karyawan")
    private Karyawan karyawan;


}
