package br.gabriel.springrestspecialist.api.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummaryRequest {
    private String name;
    
    private String email;
}
