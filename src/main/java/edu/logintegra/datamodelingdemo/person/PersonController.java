package edu.logintegra.datamodelingdemo.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/list")
    public Iterable<Person> list() {
        Iterable<Person> people = personRepository.findAll();

        for (Person person : people) {
            System.out.println("P: " + person);
        }

        return people;
    }

    @PostMapping("/save")
    public Person save(@RequestParam String username, @RequestParam String password) {
        Person person = new Person(username, password, true);
        return personRepository.save(person);
    }

    @GetMapping("/get")
    public Optional<Person> get(@RequestParam Long id) {
        return personRepository.findById(id);
    }

    @GetMapping("/show")
    public Optional<Person> get(@RequestParam String username) {
        return personRepository.findByUsernameAndEnabled(username, true);
    }

    @GetMapping("/list-sorted")
    public Iterable<Person> listSorted(@RequestParam String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return personRepository.findEnabledUsersCreatedAfter(sdf.parse(dateString));
    }
}
