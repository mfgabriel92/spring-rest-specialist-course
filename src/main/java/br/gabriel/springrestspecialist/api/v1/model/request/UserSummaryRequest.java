package br.gabriel.springrestspecialist.api.v1.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummaryRequest {
    @ApiModelProperty(required = true)
    @NotBlank
    private String name;
    
    @ApiModelProperty(required = true)
    @NotBlank
    @Email
    private String email;
}
