package com.example.springBootDemo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.springBootDemo.modules.test.entity.City;
import com.example.springBootDemo.modules.test.entity.Country;

@Repository
@Mapper
public interface CountryDao {

	/**
	 * #{countryId} = select * from m_country where country_id =? ${countryId} =
	 * select * from m_country where country_id =3
	 * 
	 * @Results ---- 封装结果集，对于联表查询的字段，可调用已有的方法getCitiesByCountryId column ---- 对应
	 *          select 查询后的某个字段名，作为映射实体bean属性 或者 作为调用方法的参数 property ---- 对应 实体 bean
	 *          属性
	 *          1、country_id封装了两次，分别对应countryId和cities，而cities属性通过getCitiesByCountryId方法来实现，
	 *          country_id作为参数 2、结果集共享，设置id属性，调用是使用@ResultMap(value="countryResult")
	 */

	@Select("select * from m_country where country_id = #{countryId}")
	@Results(id = "countryResult", value = { @Result(column = "country_id", property = "countryId"),
			@Result(column = "country_id", property = "cities", javaType = List.class, many = @Many(select = "com.example.springBootDemo.modules.test.dao.CountryDao.getCitiesByCountryId")) })
	Country getCountryById(int countryId);

	Country getCountryById2(int countryId);

	@Select("select * from m_city where country_id = #{countryId}")
	List<City> getCitiesByCountryId(int countryId);

	@Select("select * from m_country where country_name = #{countryName}")
	@ResultMap(value = "countryResult")
	Country getCountryByName(String countryName);

	@Insert("insert into m_city (city_name,local_city_name,country_id,date_created) values (#{cityName},#{localCityName},#{countryId},#{dateCreated})")
	@Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty = "cityId")
	void insertCity(City city);

	@Update("update m_city set local_city_name = #{localCityName} where city_id = #{cityId}")
	void updateCity(City city);

	@Delete("delete from m_city where city_id = #{cityId}")
	void deleteCity(int cityId);
}
