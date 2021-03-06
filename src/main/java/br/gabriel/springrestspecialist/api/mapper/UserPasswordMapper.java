package br.gabriel.springrestspecialist.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.v1.model.request.UserPasswordRequest;
import br.gabriel.springrestspecialist.domain.model.User;

@Component
public class UserPasswordMapper {
    @Autowired
    private ModelMapper mapper;
    
    public void copyToDomainObject(UserPasswordRequest passwordRequest, User user) {
        mapper.map(passwordRequest, user);
    }
}