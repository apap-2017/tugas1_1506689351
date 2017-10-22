package com.example.tugas.dao;


import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Insert;
import com.example.tugas.model.KeluargaModel;
import com.example.tugas.model.KelurahanModel;
import com.example.tugas.model.PendudukModel;








@Mapper
public interface KeluargaMapper extends KelurahanMapper
{
	
	 @Select("select * from keluarga where id = #{id}")
	 @Results(value = 
	{@Result(property="id_kelurahan", column="id_kelurahan"),
	@Result(property="lurah", column="id_kelurahan",
	 javaType=KelurahanModel.class, 
	 many=@Many(select="selectKelurahan"))
	})
	 KeluargaModel selectKeluarga (@Param("id") int id);
	 
	 
	 @Select("select * from keluarga where nomor_kk = #{nomor_kk}")
	 @Results(value = 
	{@Result(property="id_kelurahan", column="id_kelurahan"),
	 @Result(property="id", column="id"),
	 @Result(property="lurah", column="id_kelurahan",
	 javaType=KelurahanModel.class, 
	 many=@Many(select="selectKelurahan")), 
		@Result(property="anggota", column="id",
		 javaType=List.class, 
		 many=@Many(select="cariAnggota"))
	})
	 KeluargaModel cariKeluarga (@Param("nomor_kk") String nomor_kk);
	 
	 
	 
	 @Select("select * from penduduk where id_keluarga = #{id}")
	 List<PendudukModel> cariAnggota (@Param("id") int id);
	 
	 @Select("select * from penduduk where id_keluarga = #{id} and is_wafat=0")
	 List<PendudukModel> cariAnggotaHidup (@Param("id") int id);
	 
	 @Select("select p.nama, p.nik, p.jenis_kelamin, p.tanggal_lahir, p.tempat_lahir from penduduk p, keluarga k where p.id_keluarga = k.id and id_kelurahan = #{id_kelurahan} ORDER BY p.tanggal_lahir ASC")
	 List<PendudukModel> cariPendudukKelurahan(@Param("id_kelurahan") int id);
	 
	 
	 @Insert("INSERT INTO keluarga (nomor_kk, alamat, RT, "
		 		+ "RW, id_kelurahan, is_tidak_berlaku) VALUES "
		 		+ "(#{nomor_kk}, #{alamat}, #{RT}, #{RW}, #{id_kelurahan}, #{is_tidak_berlaku})")
		  void addKeluarga (KeluargaModel keluarga);
	 
	 @Select("select * from keluarga where nomor_kk like (concat(#{nkkcheck},'%')) ORDER BY id DESC LIMIT 1")
	 KeluargaModel cekNKK (@Param("nkkcheck") String nkkcheck);
	 
	 @Update("UPDATE keluarga SET nomor_kk= #{nomor_kk}, alamat = #{alamat}, RT = #{RT}, "
		 		+ "RW = #{RW}, id_kelurahan = #{id_kelurahan}, is_tidak_berlaku= #{is_tidak_berlaku} where id=#{id}")
	  void updateKeluarga (KeluargaModel keluarga);
}
