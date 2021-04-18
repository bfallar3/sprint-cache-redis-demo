package com.benjsoft.sprintcacheredisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService _personService;

    @Autowired
    public PersonController(PersonService personService) {
        this._personService = personService;
    }

    @GetMapping("/persons")
    public List<Person> get()
    {
        return _personService.get();
    }

    @PostMapping("/person")
    public Person create(@RequestBody Person person)
    {
        return _personService.create(person);
    }

    @PutMapping("/person/{id}")
    public Person update(@PathVariable String id, @RequestBody Person person)
    {
        return _personService.update(id, person);
    }

    @DeleteMapping("/person/{id}")
    public void delete(@PathVariable String id)
    {
        _personService.delete(id);
    }

    @GetMapping("/persons/{id}")
    public Person get(@PathVariable String id)
    {
        return _personService.getById(id);
    }
}
