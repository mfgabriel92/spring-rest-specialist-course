package br.gabriel.springrestspecialist.api.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.request.GroupRequest;
import br.gabriel.springrestspecialist.api.model.response.GroupResponse;
import br.gabriel.springrestspecialist.domain.model.Group;

@Component
public class GroupMapper {
    @Autowired
    private ModelMapper mapper;
    
    public GroupResponse toModel(Group group) {
        return mapper.map(group, GroupResponse.class);
    }
    
    public List<GroupResponse> toCollectionModel(List<Group> groups) {
        return groups.stream().map(group -> toModel(group)).collect(Collectors.toList());
    }
    
    public Group toDomainObject(GroupRequest groupRequest) {
        return mapper.map(groupRequest, Group.class);
    }
    
    public void copyToDomainObject(GroupRequest groupRequest, Group group) {
        mapper.map(groupRequest, group);
    }
}