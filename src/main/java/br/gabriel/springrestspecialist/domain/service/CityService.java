package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.model.State;
import br.gabriel.springrestspecialist.domain.repository.CityRepository;
import br.gabriel.springrestspecialist.domain.repository.StateRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository repository;
	
	@Autowired
	private StateRepository stateRepository;

	@Transactional
	public City save(City city) {
		Integer stateId = city.getState().getId();
		State state = stateRepository.findOrFail(stateId);

		city.setState(state);

		return repository.save(city);
	}

	@Transactional
	public void deleteById(Integer id) {
		repository.deleteOrFail(id);
	}
}