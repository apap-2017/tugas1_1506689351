package com.example.tugas.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanModel {
	
	public String id;
	public String kode_kelurahan;
	public String kode_pos;
	public String nama_kelurahan;
	public String id_kecamatan;
	public KecamatanModel camat;

	
	
	
}
