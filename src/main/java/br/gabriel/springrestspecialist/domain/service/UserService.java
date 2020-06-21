package br.gabriel.springrestspecialist.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.model.Group;
import br.gabriel.springrestspecialist.domain.model.User;
import br.gabriel.springrestspecialist.domain.repository.GroupRepository;
import br.gabriel.springrestspecialist.domain.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	@Autowired
    private GroupRepository groupRepository;

	@Transactional
	public User save(User user) {
	    repository.detach(user);
	    Optional<User> existingUser = repository.findByEmail(user.getEmail());
	    
	    if (existingUser.isPresent() && !existingUser.get().equals(user)) {
	        throw new ApiException(String.format("Another user is alredy using the e-mail %s", user.getEmail()));
	    }
	    
		return repository.save(user);
	}
	
	@Transactional
	public void changePassword(Integer id, String currentPassword, String newPassword) {
	    User user = repository.findOrFail(id);
	    
	    if (!user.isPasswordCorrect(currentPassword)) {
	        throw new ApiException("The current password does not match the user");
	    }
	    
	    if (user.getPassword().equals(newPassword)) {
	        throw new ApiException("You may not use the same password");
	    }
	    
	    user.setPassword(newPassword);
	}

	@Transactional
	public void deleteById(Integer id) {
	    repository.deleteOrFail(id);
	}
	
	@Transactional
    public void addToGroup(Integer id, Integer groupId) {
        User user = repository.findOrFail(id);
        Group group = groupRepository.findOrFail(groupId);
        user.addToGroup(group);
    }
    
    @Transactional
    public void removeFromGroup(Integer id, Integer groupId) {
        User user = repository.findOrFail(id);
        Group group = groupRepository.findOrFail(groupId);
        user.removeFromGroup(group);
    }
}