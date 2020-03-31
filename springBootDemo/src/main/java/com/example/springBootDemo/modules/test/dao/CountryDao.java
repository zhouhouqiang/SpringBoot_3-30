package com.example.springBootDemo.modules.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.springBootDemo.modules.test.entity.Country;

@Repository
@Mapper
public interface CountryDao {

	/**
	 * #{countryId} = select * from m_country where country_id =? ${countryId} =
	 * select * from m_country where country_id =3
	 */
	@Select("select * from m_country where country_id = #{countryId}")
	Country getCountryById(int countryId);
}
