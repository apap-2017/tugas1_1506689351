package com.example.tugas.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KecamatanModel{
	
	public String id;
	public String kode_kecamatan;
	public String id_kota;
	public String nama_kecamatan;
	public KotaModel kota;
	

	
	
	
}
