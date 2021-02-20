package edu.logintegra.datamodelingdemo.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    void save() {
        Person person = new Person();

        System.out.println("Nowy użytkownik: " + person);

        personRepository.save(person);
    }
}
