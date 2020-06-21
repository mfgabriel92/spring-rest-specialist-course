package br.gabriel.springrestspecialist.api.model.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantIdsRequest {
    private List<Integer> ids;
}
