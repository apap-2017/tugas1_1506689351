package com.example.tugas.dao;



import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.tugas.model.KecamatanModel;






@Mapper
public interface KecamatanMapper extends KotaMapper
{
	
	 
	 @Select("select * from kecamatan where id = #{id}")
	 @Results(value = 
	{@Result(property="kota", column="id_kota",
	 javaType=KecamatanModel.class, 
	 many=@Many(select="selectKota"))
	})
	 KecamatanModel selectKecamatan (@Param("id") int id);
	 
	 
	@Select("select * from kecamatan where id_kota = #{id_kota}")
	List<KecamatanModel> selectAllKecamatan(@Param("id_kota") int id_kota);
	 

}
