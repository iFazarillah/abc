package com.abc.abc.service;

import java.util.List;

import com.abc.abc.model.Karyawan;

public interface KaryawanService {
	public Karyawan save(Karyawan obj);

	public Karyawan update(Karyawan obj);

	public List<Karyawan> deleted(Long id);

	public List<Karyawan> dataKaryawan(int row, int page);

	public Karyawan findById(long kry);

}
