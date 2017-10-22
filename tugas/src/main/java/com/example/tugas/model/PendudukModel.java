package com.example.tugas.model;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel
{
    public int id;
    public String nik;
    public String nama;
    public String tempat_lahir;
    public String tanggal_lahir;
    public int jenis_kelamin;
    public int is_wni;
    public int id_keluarga;
    public String agama;
    public String pekerjaan;
    public String status_perkawinan;
    public String status_dalam_keluarga;
    public String golongan_darah;
    public int is_wafat;
    public String nikcheck;
    public KeluargaModel keluarga;
    
    

}
