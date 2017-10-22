package com.example.tugas.dao;




import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.tugas.model.KecamatanModel;

import com.example.tugas.model.KelurahanModel;






@Mapper
public interface KelurahanMapper extends KecamatanMapper
{
	
	 
	 @Select("select * from kelurahan where id = #{id}")
	 @Results(value = 
	{@Result(property="camat", column="id_kecamatan",
	 javaType=KecamatanModel.class, 
	 many=@Many(select="selectKecamatan"))
	})
	 KelurahanModel selectKelurahan (@Param("id") int id);
	 
	 
	 @Select("select * from kelurahan")
	 List<KelurahanModel> selectAllKelurahan ();
	 
	 @Select("select * from kelurahan where id_kecamatan = #{id_kecamatan}")
	 List<KelurahanModel> selectAllKelurahanID (@Param("id_kecamatan") int id_kecamatan);
	
}
