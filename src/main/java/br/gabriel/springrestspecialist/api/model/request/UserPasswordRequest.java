package br.gabriel.springrestspecialist.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordRequest {
    @NotBlank
    private String currentPassword;
    
    @NotBlank
    @Size(min = 6)
    private String newPassword;
}
