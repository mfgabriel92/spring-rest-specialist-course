package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gabriel.springrestspecialist.domain.model.Kitchen;
import br.gabriel.springrestspecialist.domain.repository.KitchenRepository;

@Service
public class KitchenService {
	@Autowired
	private KitchenRepository repository;
	
	public Kitchen save(Kitchen kitchen) {
		return repository.save(kitchen);
	}
}