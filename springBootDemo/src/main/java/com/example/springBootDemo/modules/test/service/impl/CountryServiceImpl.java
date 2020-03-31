package com.example.springBootDemo.modules.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBootDemo.modules.test.dao.CountryDao;
import com.example.springBootDemo.modules.test.entity.Country;
import com.example.springBootDemo.modules.test.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countrydao;

	@Override
	public Country getCountryById(int countryId) {

		return countrydao.getCountryById(countryId);
	}

}
