package com.example.demo;

import groovy.lang.Binding;
import org.springframework.web.bind.annotation.*;
import groovy.lang.GroovyShell;

import java.util.List;

@RestController
public class ScriptsController {

    private final ScriptsRepository repository;

    public ScriptsController(ScriptsRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/scripts")
    List<Scripts> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/scripts")
    Scripts newScript(@RequestBody Scripts newScript){
        return repository.save(newScript);
    }

    @PostMapping("scripts/run/{name}")
    Object runScript(@PathVariable String name, @RequestBody List<Object> params){

        List<Scripts> list = repository.findByName(name);
        if(list.isEmpty()) {
            throw new ScriptNotFoundException(name);
        }
        else {
            Binding binding = new Binding();
            int i=1;
            for (Object par: params) {
                binding.setVariable("p" + String.valueOf(i), par);
                i+=1;
            }
            GroovyShell shell = new GroovyShell(binding);
            return shell.evaluate(list.get(0).getScript());
        }
    }

    @GetMapping("/scripts/{id}")
    Scripts one(@PathVariable Integer id){
        return repository.findById(id)
                .orElseThrow(()-> new ScriptNotFoundException(id));
    }
    @PutMapping("/scripts/{id}")
    Scripts replaceScript(@RequestBody Scripts newScript, @PathVariable Integer id){
        return repository.findById(id)
                .map(script -> {
                    script.setName(newScript.getName());
                    script.setScript(newScript.getScript());
                    return repository.save(script);
                })
                .orElseGet(()->{
                    newScript.setId(id);
                    return repository.save(newScript);
                });
    }



    @DeleteMapping("/scripts/{id}")
    void deleteScript(@PathVariable Integer id) {
        repository.deleteById(id);
    }


}
