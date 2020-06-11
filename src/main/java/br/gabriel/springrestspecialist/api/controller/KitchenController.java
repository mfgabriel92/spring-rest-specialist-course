package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.domain.model.Kitchen;
import br.gabriel.springrestspecialist.domain.repository.KitchenRepository;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
    @Autowired
    private KitchenRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Kitchen> findAll() {
        return repository.findAll();
    }
    
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public List<Kitchen> findAllXML() {
        return repository.findAll();
    }
}