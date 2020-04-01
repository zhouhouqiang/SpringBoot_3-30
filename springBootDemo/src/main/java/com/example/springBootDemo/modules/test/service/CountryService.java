package com.example.springBootDemo.modules.test.service;

import java.util.List;

import com.example.springBootDemo.modules.test.entity.City;
import com.example.springBootDemo.modules.test.entity.Country;
import com.github.pagehelper.PageInfo;

public interface CountryService {

	Country getCountryById(int countryId);

	List<City> getCitiesByCountryId(int countryId);

	Country getCountryByName(String countryName);

	PageInfo<City> getCitiesByPage(int countryId, int currentPage, int pageSize);

	City insertCity(City city);

	City updateCity(City city);

	void deleteCity(int cityId);
}
