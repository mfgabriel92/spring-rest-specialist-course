package br.gabriel.springrestspecialist.domain.repository;

import java.util.List;

import br.gabriel.springrestspecialist.domain.model.Permission;

public interface PermissionRepository {
	List<Permission> findAll();
	
	Permission findById(Integer id);
	
	Permission save(Permission permission);
	
	void delete(Permission permission);
}
