package br.gabriel.springrestspecialist.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Integer id; 
    
    private String name;
    
    private String email;
}
