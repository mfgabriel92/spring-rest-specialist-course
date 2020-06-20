package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.Group;
import br.gabriel.springrestspecialist.domain.model.Permission;
import br.gabriel.springrestspecialist.domain.repository.GroupRepository;
import br.gabriel.springrestspecialist.domain.repository.PermissionRepository;

@Service
public class GroupService {
	@Autowired
	private GroupRepository repository;
	
	@Autowired
    private PermissionRepository permissionRepository;

	@Transactional
	public Group save(Group state) {
		return repository.save(state);
	}

	@Transactional
	public void deleteById(Integer id) {
	    repository.deleteOrFail(id);
	}
	
	@Transactional
    public void addPermission(Integer id, Integer permissionId) {
        Group group = repository.findOrFail(id);
        Permission permission = permissionRepository.findOrFail(permissionId);
        group.addPermission(permission);
    }
    
    @Transactional
    public void removePermission(Integer id, Integer permissionId) {
        Group group = repository.findOrFail(id);
        Permission permission = permissionRepository.findOrFail(permissionId);
        group.removePermission(permission);
    }
}