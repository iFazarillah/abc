package com.abc.abc.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "detail_karyawan")
@Where(clause = "deleted_date is null")
public class DetailKaryawan implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nik", length = 45)
    private String nik;

    @Column(name = "npwp", length = 10)
    private String npwp;

    @OneToOne (targetEntity = Karyawan.class, cascade = CascadeType.ALL)
    @JoinColumn(name="id_karyawan", referencedColumnName = "id")
    private Karyawan karyawan;


}
