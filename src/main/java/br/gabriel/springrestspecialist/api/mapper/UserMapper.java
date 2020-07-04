package br.gabriel.springrestspecialist.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.v1.model.request.UserRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.UserResponse;
import br.gabriel.springrestspecialist.domain.model.User;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper mapper;
    
    public UserResponse toModel(User user) {
        return mapper.map(user, UserResponse.class);
    }
    
    public List<UserResponse> toCollectionModel(List<User> users) {
        return users.stream().map(user -> toModel(user)).collect(Collectors.toList());
    }
    
    public User toDomainObject(UserRequest userRequest) {
        return mapper.map(userRequest, User.class);
    }
    
    public void copyToDomainObject(UserRequest userRequest, User user) {
        mapper.map(userRequest, user);
    }
}