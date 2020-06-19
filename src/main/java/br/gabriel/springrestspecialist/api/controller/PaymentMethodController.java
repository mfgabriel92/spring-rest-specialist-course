package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.model.mapper.PaymentMethodMapper;
import br.gabriel.springrestspecialist.api.model.request.PaymentMethodRequest;
import br.gabriel.springrestspecialist.api.model.response.PaymentMethodResponse;
import br.gabriel.springrestspecialist.domain.model.PaymentMethod;
import br.gabriel.springrestspecialist.domain.repository.PaymentMethodRepository;
import br.gabriel.springrestspecialist.domain.service.PaymentMethodService;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {
	@Autowired
	private PaymentMethodRepository repository;
	
	@Autowired
	private PaymentMethodService service;
	
	@Autowired
	private PaymentMethodMapper mapper;

	@GetMapping
	public List<PaymentMethodResponse> findAll() {
		return mapper.toCollectionModel(repository.findAll());
	}
	
	@GetMapping("{id}")
	public PaymentMethodResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentMethodResponse save(@RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
	    PaymentMethod paymentMethod = mapper.toDomainObject(paymentMethodRequest);
		return mapper.toModel(service.save(paymentMethod));
	}

	@PutMapping("{id}")
	public PaymentMethodResponse save(@PathVariable Integer id, @RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
		PaymentMethod paymentMethod = repository.findOrFail(id);
		mapper.copyToDomainObject(paymentMethodRequest, paymentMethod);
		return mapper.toModel(service.save(paymentMethod));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}