package br.gabriel.springrestspecialist.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenRequest {
    @NotNull
    private String name;
}
