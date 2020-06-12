package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.domain.model.Kitchen;
import br.gabriel.springrestspecialist.domain.repository.KitchenRepository;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
    @Autowired
    private KitchenRepository repository;

    @GetMapping
    public List<Kitchen> findAll() {
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> findById(@PathVariable Integer id) {
        Kitchen kitchen = repository.findById(id);
        return ResponseEntity.ok(kitchen);
    }
}