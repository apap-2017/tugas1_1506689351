package com.example.tugas.service;



import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.tugas.model.KecamatanModel;
import com.example.tugas.model.KeluargaModel;
import com.example.tugas.model.KelurahanModel;
import com.example.tugas.model.KotaModel;
import com.example.tugas.model.PendudukModel;


public interface SidukService
{

	PendudukModel selectPenduduk(String nik);


	KeluargaModel selectKeluarga(int id);


	KeluargaModel cariKeluarga(String nkk);


	void addPenduduk(PendudukModel penduduk);

	PendudukModel cekPenduduk (String nikcheck);
	
	List<KelurahanModel> selectAllKelurahan ();
	
	KelurahanModel selectKelurahan (int id);
	 
	void addKeluarga (KeluargaModel keluarga);
	 
	KeluargaModel cekNKK (String nkkcheck);
	
	List<KotaModel> selectAllKota();
	
	List<KecamatanModel> selectAllKecamatan(int kt);
 
	KotaModel selectKota (int id);

	KecamatanModel selectKecamatan(int id);

	List<KelurahanModel> selectAllKelurahanID(int id_kelurahan);


	List<PendudukModel> cariPendudukKelurahan(int id);

	void updatePenduduk(PendudukModel p);


	void updateKeluarga(KeluargaModel keluarga);


	List<PendudukModel> cariAnggota(int id_keluarga);

	List<PendudukModel> cariAnggotaHidup(int id_keluarga);

}
