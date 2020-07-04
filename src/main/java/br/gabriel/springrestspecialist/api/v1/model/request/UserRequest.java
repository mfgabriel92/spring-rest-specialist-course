package br.gabriel.springrestspecialist.api.v1.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @ApiModelProperty(required = true)
    @NotBlank
    private String name;
    
    @ApiModelProperty(required = true)
    @NotBlank
    @Email
    private String email;
    
    @ApiModelProperty(required = true)
    @NotBlank
    @Size(min = 6)
    private String password;
}
