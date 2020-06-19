package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.Group;
import br.gabriel.springrestspecialist.domain.repository.GroupRepository;

@Service
public class GroupService {
	@Autowired
	private GroupRepository repository;

	@Transactional
	public Group save(Group state) {
		return repository.save(state);
	}

	@Transactional
	public void deleteById(Integer id) {
	    repository.deleteOrFail(id);
	}
}