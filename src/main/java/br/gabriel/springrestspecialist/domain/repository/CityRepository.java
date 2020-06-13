package br.gabriel.springrestspecialist.domain.repository;

import java.util.List;

import br.gabriel.springrestspecialist.domain.model.City;

public interface CityRepository {
	List<City> findAll();
	
	City findById(Integer id);
	
	City save(City city);
	
	void deleteById(Integer id);
}
