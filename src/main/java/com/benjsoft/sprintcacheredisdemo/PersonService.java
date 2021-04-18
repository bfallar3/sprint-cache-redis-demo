package com.benjsoft.sprintcacheredisdemo;

import java.util.List;

public interface PersonService {
    Person create(Person person);
    Person update(String id, Person person);
    void delete(String id);
    List<Person> get();
    Person getById(String id);
}
