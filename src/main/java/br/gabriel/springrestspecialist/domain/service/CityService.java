package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gabriel.springrestspecialist.domain.exception.ResourceInUseExeption;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
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

	public City save(City city) {
		Integer stateId = city.getState().getId();
		State state = stateRepository.findById(stateId)
			.orElseThrow(() -> new ResourceNotFoundExeption(String.format("State ID %d does not exist", stateId)));

		city.setState(state);

		return repository.save(city);
	}

	public void deleteById(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundExeption(String.format("The city ID %d does not exist", id));
		} catch (DataIntegrityViolationException e) {
			throw new ResourceInUseExeption(String.format("The city ID %d is being used by a restaurant", id));
		}
	}
}