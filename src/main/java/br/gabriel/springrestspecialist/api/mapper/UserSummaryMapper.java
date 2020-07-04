package br.gabriel.springrestspecialist.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.v1.model.request.UserSummaryRequest;
import br.gabriel.springrestspecialist.domain.model.User;

@Component
public class UserSummaryMapper {
    @Autowired
    private ModelMapper mapper;
    
    public void copyToDomainObject(UserSummaryRequest userRequest, User user) {
        mapper.map(userRequest, user);
    }
}