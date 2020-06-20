package br.gabriel.springrestspecialist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID> extends JpaRepository<T, ID> {
	T findOrFail(ID id);
	
	void deleteOrFail(ID id);
	
	void detach(T entity);
}
