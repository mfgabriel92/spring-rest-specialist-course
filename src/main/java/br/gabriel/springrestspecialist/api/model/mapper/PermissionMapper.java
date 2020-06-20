package br.gabriel.springrestspecialist.api.model.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.response.PermissionResponse;
import br.gabriel.springrestspecialist.domain.model.Permission;

@Component
public class PermissionMapper {
    @Autowired
    private ModelMapper mapper;
    
    public PermissionResponse toModel(Permission permission) {
        return mapper.map(permission, PermissionResponse.class);
    }
    
    public List<PermissionResponse> toCollectionModel(Collection<Permission> permissions) {
        return permissions.stream().map(permission -> toModel(permission)).collect(Collectors.toList());
    }
}