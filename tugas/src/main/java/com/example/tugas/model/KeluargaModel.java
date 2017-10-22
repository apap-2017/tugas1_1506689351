package com.example.tugas.model;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel  {
	
	public int id;
	public String nomor_kk;
	public String alamat;
	public String RT;
	public String RW;
	public int id_kelurahan;
	public String nkkcheck;
	public int is_tidak_berlaku;
	public KelurahanModel lurah;
	public List<PendudukModel> anggota;
	public List<KelurahanModel> kelurahan;
	
}
