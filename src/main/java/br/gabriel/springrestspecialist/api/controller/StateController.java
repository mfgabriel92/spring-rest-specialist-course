package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.domain.model.State;
import br.gabriel.springrestspecialist.domain.repository.StateRepository;

@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateRepository repository;

    @GetMapping
    public List<State> findAll() {
        return repository.findAll();
    }
}