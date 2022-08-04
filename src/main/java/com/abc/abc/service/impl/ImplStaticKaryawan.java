package com.abc.abc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abc.abc.model.Karyawan;
import com.abc.abc.service.KaryawanServiceStatic;

@Service
public class ImplStaticKaryawan implements KaryawanServiceStatic {
	static List<Karyawan> listKaryawan = new ArrayList<>();

	@Override
	public Karyawan save(Karyawan kry) {
		// TODO Auto-generated method stub
		long num = (long) (Math.random() * 50 + 1);
		kry.setId(num);
		listKaryawan.add(kry);
		System.out.println(kry.getId());
		return kry;
	}

	@Override
	public Karyawan update(Karyawan kry) {
		for (Karyawan data : listKaryawan) {
			if (kry.getId() == data.getId()) {
				Karyawan update = new Karyawan();
				update.setNama(kry.getNama());
				update.setId(kry.getId());
				update.setJk(kry.getJk());
				update.setDob(kry.getDob());
				update.setAlamat(kry.getAlamat());
				update.setStatus(kry.getStatus());
				listKaryawan.remove(data);
				listKaryawan.add(update);
				return update;
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Karyawan> deleted(Long id) {
		// TODO Auto-generated method stub
		for (Karyawan data : listKaryawan) {
			if (id == data.getId()) {
				Karyawan update = new Karyawan();
				update.setNama(data.getNama());
				update.setId(data.getId());
				update.setJk(data.getJk());
				update.setDob(data.getDob());
				update.setAlamat(data.getAlamat());
				update.setStatus(data.getStatus());
				listKaryawan.remove(data);

				return listKaryawan;
			}
		}
		return null;
	}

	public List<Karyawan> dataKaryawan(int row, int page) {
		// TODO Auto-generated method stub
		return listKaryawan;

	}

	@Override
	public Karyawan findById(long kry) {
		// TODO Auto-generated method stub
		for (Karyawan data : listKaryawan) {
			if (kry == data.getId()) {
				return data;
			}
		}
		return null;
	}

}
