package com.example.springBootDemo.modules.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.springBootDemo.modules.test.dao.CountryDao;
import com.example.springBootDemo.modules.test.entity.City;
import com.example.springBootDemo.modules.test.entity.Country;
import com.example.springBootDemo.modules.test.service.CountryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countrydao;

	@Override
	public Country getCountryById(int countryId) {
		return countrydao.getCountryById(countryId);
	}

	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		//	return countrydao.getCitiesByCountryId(countryId);
		return Optional.ofNullable(countrydao.getCitiesByCountryId(countryId)).orElse(Collections.emptyList());
	}

	@Override
	public Country getCountryByName(String countryName) {
		return countrydao.getCountryByName(countryName);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageInfo<City> getCitiesByPage(int countryId, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<City> cities = Optional.ofNullable(countrydao.getCitiesByCountryId(countryId))
				.orElse(Collections.emptyList());
		return new PageInfo(cities);
	}

	@Override
	public City insertCity(City city) {
		countrydao.insertCity(city);
		return city;
	}

	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = ArithmeticException.class)
	@Override
	public City updateCity(City city) {
		countrydao.updateCity(city);
		//	int i = 1 / 0;
		return city;
	}

	@Override
	public void deleteCity(int cityId) {
		countrydao.deleteCity(cityId);
	}

}
