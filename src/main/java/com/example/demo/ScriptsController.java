package com.example.demo;


import groovy.lang.Binding;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import groovy.lang.GroovyShell;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class ScriptsController {

    private final ScriptsRepository repository;

    private final ScriptModelAssembler assembler;

    public ScriptsController(ScriptsRepository repository, ScriptModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/scripts")
    CollectionModel<EntityModel<Scripts>> all() {
        List<EntityModel<Scripts>> scripts = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(scripts,
                linkTo(methodOn(ScriptsController.class).all()).withSelfRel());
    }

    @PostMapping("/scripts")
    ResponseEntity<?> newScript(@RequestBody Scripts newScript){
        EntityModel<Scripts> entityModel = assembler.toModel(repository.save(newScript));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping("scripts/run/{name}")
    Object runScript(@PathVariable String name, @RequestBody(required = false) ScriptParameters parameters){

        List<Scripts> list = repository.findByName(name);
        if(list.isEmpty()) {
            throw new ScriptNotFoundException(name);
        }
        else {
            Binding binding = new Binding();
            GroovyShell shell = new GroovyShell(binding);
            if(parameters == null)
            {
                return shell.evaluate(list.get(0).getScript());
            }
            System.out.println(parameters.getParameters());
            parameters.getParameters().forEach( (key, value) -> binding.setVariable( '$' + key, value));
            return shell.evaluate(list.get(0).getScript());

        }

    }

    @GetMapping("/scripts/{id}")
    EntityModel<Scripts> one(@PathVariable Integer id){
        Scripts script = repository.findById(id)
                .orElseThrow(()-> new ScriptNotFoundException(id));
        return assembler.toModel(script);
    }
    @PutMapping("/scripts/{id}")
    ResponseEntity<?> replaceScript(@RequestBody Scripts newScript, @PathVariable Integer id){
        Scripts updatedScript = repository.findById(id)
                .map(script -> {
                    script.setName(newScript.getName());
                    script.setScript(newScript.getScript());
                    return repository.save(script);
                })
                .orElseGet(()->{
                    newScript.setId(id);
                    return repository.save(newScript);
                });
        EntityModel<Scripts> entityModel = assembler.toModel(updatedScript);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }



    @DeleteMapping("/scripts/{id}")
    ResponseEntity<?> deleteScript(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }




}
