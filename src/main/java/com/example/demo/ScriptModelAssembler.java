package com.example.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ScriptModelAssembler implements RepresentationModelAssembler<Scripts, EntityModel<Scripts>> {

    @Override
    public EntityModel<Scripts> toModel(Scripts script) {

        return EntityModel.of(script, //
                linkTo(methodOn(ScriptsController.class).one(script.getId())).withSelfRel(),
                linkTo(methodOn(ScriptsController.class).all()).withRel("scripts"));
    }
}
