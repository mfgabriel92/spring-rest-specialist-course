package br.gabriel.springrestspecialist.api.model.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.request.PaymentMethodRequest;
import br.gabriel.springrestspecialist.api.model.response.PaymentMethodResponse;
import br.gabriel.springrestspecialist.domain.model.PaymentMethod;

@Component
public class PaymentMethodMapper {
    @Autowired
    private ModelMapper mapper;
    
    public PaymentMethodResponse toModel(PaymentMethod paymentMethod) {
        return mapper.map(paymentMethod, PaymentMethodResponse.class);
    }
    
    public List<PaymentMethodResponse> toCollectionModel(Collection<PaymentMethod> paymentMethods) {
        return paymentMethods.stream().map(paymentMethod -> toModel(paymentMethod)).collect(Collectors.toList());
    }
    
    public PaymentMethod toDomainObject(PaymentMethodRequest paymentMethodRequest) {
        return mapper.map(paymentMethodRequest, PaymentMethod.class);
    }
    
    public void copyToDomainObject(PaymentMethodRequest paymentMethodRequest, PaymentMethod paymentMethod) {
        mapper.map(paymentMethodRequest, paymentMethod);
    }
}