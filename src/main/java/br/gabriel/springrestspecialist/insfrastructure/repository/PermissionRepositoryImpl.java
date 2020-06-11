package br.gabriel.springrestspecialist.insfrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.Permission;
import br.gabriel.springrestspecialist.domain.repository.PermissionRepository;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permission> findAll() {
		TypedQuery<Permission> query = manager.createQuery("FROM Permission", Permission.class);
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public Permission save(Permission permission) {
		return manager.merge(permission);
	}
	
	@Override
	public Permission findById(Integer id) {
		return manager.find(Permission.class, id);
	}
	
	@Override
	@Transactional
	public void delete(Permission permission) {
		permission = findById(permission.getId());
		manager.remove(permission);
	}
}
