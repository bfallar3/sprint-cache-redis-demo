package com.benjsoft.sprintcacheredisdemo;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = {"person"} )
public class PersonServiceImpl implements PersonService {
    private final static List<Person> persons = new ArrayList<>();

    @Override
    @CachePut(key = "#person.id")
    public Person create(Person person) {
        person.setId(UUID.randomUUID().toString());
        persons.add(person);
        return person;
    }

    @Override
    @CacheEvict(key = "#id")
    public Person update(String id, Person person) {
        Person item = new Person();
        Optional<Person> pers = persons.stream().filter(p -> p.getId().equals(id)).findFirst();
        if(pers.isPresent()) {
            item.setName(person.getName());
            item.setAge(person.getAge());
            item.setBirthdate(person.getBirthdate());
            item.setId(id);
            persons.remove(pers.get());
            persons.add(item);
        }
        return item;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(String id) {
        Optional<Person> pers = persons.stream().filter(p -> p.getId().equals(id)).findFirst();
        pers.ifPresent(persons::remove);
    }

    @Override
    //@Cacheable(value = "personCache")
    public List<Person> get() {
        return persons;
    }

    @Override
    @Cacheable(key = "#id")
    public Person getById(String id) {
        Optional<Person> pers = persons.stream().filter(p -> p.getId().equals(id)).findFirst();
        return pers.orElse(null);
    }
}
