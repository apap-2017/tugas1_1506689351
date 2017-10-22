package com.example.tugas.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas.dao.KecamatanMapper;
import com.example.tugas.dao.KeluargaMapper;
import com.example.tugas.dao.KelurahanMapper;
import com.example.tugas.dao.KotaMapper;
import com.example.tugas.dao.PendudukMapper;
import com.example.tugas.model.KecamatanModel;
import com.example.tugas.model.KeluargaModel;
import com.example.tugas.model.KelurahanModel;
import com.example.tugas.model.KotaModel;
import com.example.tugas.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SidukServiceDatabase implements SidukService
{
    @Autowired
    private PendudukMapper sidukMapper;

    @Autowired
    private KeluargaMapper keluargaMapper;
    
    @Autowired
    private KelurahanMapper kelurahanMapper;
    
    @Autowired
    private KecamatanMapper kecamatanMapper;
    
    @Autowired
    private KotaMapper kotaMapper;
    
    @Override
    public PendudukModel selectPenduduk (String nik)
    {
        log.info ("select student with npm {}", nik);
        return sidukMapper.selectPenduduk (nik);
    }

    @Override
    public KeluargaModel selectKeluarga (int id)
    {
        log.info ("select student with npm {}", id);
        return keluargaMapper.selectKeluarga (id);
    }
    
    @Override
    public KeluargaModel cariKeluarga (String nkk)
    {
        log.info ("select student with npm {}", nkk);
        return keluargaMapper.cariKeluarga (nkk);
    }

	@Override
	public void addPenduduk(PendudukModel penduduk) {
		sidukMapper.addPenduduk(penduduk);
		log.info(penduduk.nik + " " + penduduk.nama + " " + penduduk.status_perkawinan);
		
	}

	@Override
	public PendudukModel cekPenduduk(String nikcheck) {
		// TODO Auto-generated method stub
		return sidukMapper.cekPenduduk(nikcheck);
	}

	@Override
	public List<KelurahanModel> selectAllKelurahan() {
		// TODO Auto-generated method stub
		return kelurahanMapper.selectAllKelurahan();
	}

	@Override
	public KelurahanModel selectKelurahan(int id) {
		// TODO Auto-generated method stub
		return kelurahanMapper.selectKelurahan(id);
	}

	@Override
	public void addKeluarga(KeluargaModel keluarga) {
		keluargaMapper.addKeluarga(keluarga);
		
	}

	@Override
	public KeluargaModel cekNKK(String nkkcheck) {
		// TODO Auto-generated method stub
		return keluargaMapper.cekNKK(nkkcheck);
	}

	@Override
	public List<KotaModel> selectAllKota() {
		// TODO Auto-generated method stub
		return kotaMapper.selectAllKota();
	}

	@Override
	public List<KecamatanModel> selectAllKecamatan(int id_kota) {
		// TODO Auto-generated method stub
		return kecamatanMapper.selectAllKecamatan(id_kota);
	}

	@Override
	public KotaModel selectKota(int id) {
		// TODO Auto-generated method stub
		return kotaMapper.selectKota(id);
	}

	@Override
	public KecamatanModel selectKecamatan(int id) {
		// TODO Auto-generated method stub
		return kecamatanMapper.selectKecamatan(id);
	}

	@Override
	public List<KelurahanModel> selectAllKelurahanID(int id_kelurahan) {
		// TODO Auto-generated method stub
		return kelurahanMapper.selectAllKelurahanID(id_kelurahan);
	}

	@Override
	public List<PendudukModel> cariPendudukKelurahan(int id) {
		// TODO Auto-generated method stub
		return keluargaMapper.cariPendudukKelurahan(id);
	}

	@Override
	public void updatePenduduk(PendudukModel p) {
		// TODO Auto-generated method stub
		sidukMapper.updatePenduduk(p);
		
	}

	@Override
	public void updateKeluarga(KeluargaModel keluarga) {
		// TODO Auto-generated method stub
		keluargaMapper.updateKeluarga(keluarga);
		
	}

	@Override
	public List<PendudukModel> cariAnggota(int id_keluarga) {
		// TODO Auto-generated method stub
		return keluargaMapper.cariAnggota(id_keluarga);
	}

	@Override
	public List<PendudukModel> cariAnggotaHidup(int id_keluarga) {
		// TODO Auto-generated method stub
		return keluargaMapper.cariAnggotaHidup(id_keluarga);
	}

	
    
    
}
