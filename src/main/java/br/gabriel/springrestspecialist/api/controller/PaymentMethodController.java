package br.gabriel.springrestspecialist.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.controller.documentation.PaymentMethodDoc;
import br.gabriel.springrestspecialist.api.model.mapper.PaymentMethodMapper;
import br.gabriel.springrestspecialist.api.model.request.PaymentMethodRequest;
import br.gabriel.springrestspecialist.api.model.response.PaymentMethodResponse;
import br.gabriel.springrestspecialist.domain.model.PaymentMethod;
import br.gabriel.springrestspecialist.domain.repository.PaymentMethodRepository;
import br.gabriel.springrestspecialist.domain.service.PaymentMethodService;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController implements PaymentMethodDoc {
	@Autowired
	private PaymentMethodRepository repository;
	
	@Autowired
	private PaymentMethodService service;
	
	@Autowired
	private PaymentMethodMapper mapper;

	@Override
    @GetMapping
	public ResponseEntity<List<PaymentMethodResponse>> findAll() {
	    List<PaymentMethodResponse> paymentMethods = mapper.toCollectionModel(repository.findAll());
	    
		return ResponseEntity.ok()
		    .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
		    .body(paymentMethods);
	}
	
	@Override
    @GetMapping("{id}")
	public ResponseEntity<PaymentMethodResponse> findById(@PathVariable Integer id) {
	    PaymentMethodResponse paymentMethod = mapper.toModel(repository.findOrFail(id));
	    
	    return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
            .body(paymentMethod);
	}
	
	@Override
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentMethodResponse save(@RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
	    PaymentMethod paymentMethod = mapper.toDomainObject(paymentMethodRequest);
		return mapper.toModel(service.save(paymentMethod));
	}

	@Override
    @PutMapping("{id}")
	public PaymentMethodResponse save(@PathVariable Integer id, @RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
		PaymentMethod paymentMethod = repository.findOrFail(id);
		mapper.copyToDomainObject(paymentMethodRequest, paymentMethod);
		return mapper.toModel(service.save(paymentMethod));
	}

	@Override
    @DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}