package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.PaymentMethodController;
import br.gabriel.springrestspecialist.api.v1.model.request.PaymentMethodRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.PaymentMethodResponse;
import br.gabriel.springrestspecialist.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaymentMethodMapper implements RepresentationModelAssembler<PaymentMethod, PaymentMethodResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public PaymentMethodResponse toModel(PaymentMethod paymentMethod) {
        PaymentMethodResponse response = mapper.map(paymentMethod, PaymentMethodResponse.class);
        
        response.add(linkTo(methodOn(PaymentMethodController.class).findById(paymentMethod.getId())).withSelfRel());
        
        return response;
    }
    
    @Override
    public CollectionModel<PaymentMethodResponse> toCollectionModel(Iterable<? extends PaymentMethod> entities) {
        return RepresentationModelAssembler.super
            .toCollectionModel(entities)
            .add(linkTo(PaymentMethodController.class).withRel(IanaLinkRelations.COLLECTION));
    }
    
    public PaymentMethod toDomainObject(PaymentMethodRequest paymentMethodRequest) {
        return mapper.map(paymentMethodRequest, PaymentMethod.class);
    }
    
    public void copyToDomainObject(PaymentMethodRequest paymentMethodRequest, PaymentMethod paymentMethod) {
        mapper.map(paymentMethodRequest, paymentMethod);
    }
}
