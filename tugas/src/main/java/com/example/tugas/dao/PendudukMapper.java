package com.example.tugas.dao;




import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas.model.KeluargaModel;
import com.example.tugas.model.PendudukModel;






@Mapper
public interface PendudukMapper extends KeluargaMapper
{
	 @Select("select * from penduduk where nik = #{nik}")
	 @Results(value = 
	{@Result(property="id_keluarga", column="id_keluarga"),
	 @Result(property="keluarga", column="id_keluarga",
	 javaType=KeluargaModel.class, 
	 many=@Many(select="selectKeluarga"))
	})
	 PendudukModel selectPenduduk (@Param("nik") String nik);
   
	 
	 @Insert("INSERT INTO penduduk (nama, tempat_lahir, tanggal_lahir, "
	 		+ "jenis_kelamin, is_wni, is_wafat, golongan_darah, status_perkawinan, "
	 		+ "status_dalam_keluarga, agama, pekerjaan, id_keluarga, nik) VALUES "
	 		+ "(#{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{is_wafat}, #{golongan_darah}, "
	 		+ "#{status_perkawinan}, #{status_dalam_keluarga}, #{agama}, "
	 		+ "#{pekerjaan}, #{id_keluarga}, #{nik})")
	  void addPenduduk (PendudukModel penduduk);

	 @Select("select * from penduduk where nik like (concat(#{nikcheck},'%')) ORDER BY id DESC LIMIT 1")
	 PendudukModel cekPenduduk (@Param("nikcheck") String nikcheck);
   
	 @Update("UPDATE penduduk SET nik= #{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, "
	 		+ "tanggal_lahir = #{tanggal_lahir}, jenis_kelamin = #{jenis_kelamin}, "
	 		+ "is_wni= #{is_wni}, is_wafat= #{is_wafat}, golongan_darah= #{golongan_darah}, "
	 		+ "status_perkawinan= #{status_perkawinan}, status_dalam_keluarga= #{status_dalam_keluarga}, "
	 		+ "agama= #{agama}, pekerjaan= #{pekerjaan}, id_keluarga= #{id_keluarga} where id=#{id}")
	 void updatePenduduk (PendudukModel penduduk);
	 
}
