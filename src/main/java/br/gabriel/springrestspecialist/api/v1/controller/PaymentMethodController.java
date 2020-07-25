package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.PaymentMethodMapper;
import br.gabriel.springrestspecialist.api.v1.model.request.PaymentMethodRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.PaymentMethodResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.PaymentMethodDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.model.PaymentMethod;
import br.gabriel.springrestspecialist.domain.repository.PaymentMethodRepository;
import br.gabriel.springrestspecialist.domain.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/v1/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentMethodController implements PaymentMethodDoc {
	@Autowired
	private PaymentMethodRepository repository;
	
	@Autowired
	private PaymentMethodService service;
	
	@Autowired
	private PaymentMethodMapper mapper;

	@Override
	@Permission.Authenticated
    @GetMapping
	public ResponseEntity<List<PaymentMethodResponse>> findAll() {
	    List<PaymentMethodResponse> paymentMethods = mapper.toCollectionModel(repository.findAll());
	    
		return ResponseEntity.ok()
		    .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
		    .body(paymentMethods);
	}
	
	@Override
	@Permission.Authenticated
	@GetMapping("{id}")
	public ResponseEntity<PaymentMethodResponse> findById(@PathVariable Integer id) {
	    PaymentMethodResponse paymentMethod = mapper.toModel(repository.findOrFail(id));
	    
	    return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
            .body(paymentMethod);
	}
	
	@Override
	@Permission.Write
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentMethodResponse save(@RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
	    PaymentMethod paymentMethod = mapper.toDomainObject(paymentMethodRequest);
		return mapper.toModel(service.save(paymentMethod));
	}

	@Override
	@Permission.Write
	@PutMapping("{id}")
	public PaymentMethodResponse save(@PathVariable Integer id, @RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
		PaymentMethod paymentMethod = repository.findOrFail(id);
		mapper.copyToDomainObject(paymentMethodRequest, paymentMethod);
		return mapper.toModel(service.save(paymentMethod));
	}

	@Override
	@Permission.Delete
    @DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
