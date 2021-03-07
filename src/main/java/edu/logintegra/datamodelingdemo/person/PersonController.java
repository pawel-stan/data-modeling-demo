package edu.logintegra.datamodelingdemo.person;

import edu.logintegra.datamodelingdemo.company.Company;
import edu.logintegra.datamodelingdemo.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public PersonController(PersonRepository personRepository, CompanyRepository companyRepository, AuthorityRepository authorityRepository) {
        this.personRepository = personRepository;
        this.companyRepository = companyRepository;
        this.authorityRepository = authorityRepository;
    }

    @GetMapping("/list")
    public Iterable<Person> list() {
        return personRepository.findAll();
    }

    @PostMapping("/save")
    public Person save(@RequestParam String username, @RequestParam String password) {
        Person person = new Person(username, password, true);
        return personRepository.save(person);
    }

    @PostMapping("/saveWithCompany")
    public Person saveWithCompany(@RequestParam String username, @RequestParam String password, @RequestParam String companyName) {
        Company company = new Company(companyName);
        companyRepository.save(company);

        Person person = new Person(username, password, true);
        person.setCompany(company);
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

    /**
     * GET http://localhost:8080/people/list-sorted?dateString=2021-02-13T10:30-0000
     *
     * @param dateString data ze strefą czasową, np. 2021-02-13T10:30-0000
     * @return lista rekordów `person`
     * @throws ParseException w przypadku błędnego formatu daty
     */
    @GetMapping("/list-created-after")
    public Iterable<Person> listCreatedAfter(@RequestParam String dateString)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");

        return personRepository.findEnabledUsersCreatedAfter(sdf.parse(dateString));
    }

    @PostMapping("/disable")
    public Optional<Person> disable(@RequestParam String username) {
        Optional<Person> person = personRepository.findByUsernameAndEnabled(username, true);
        person.ifPresent((value) -> {
            value.setEnabled(false);
            personRepository.save(value);
        });
        return person;
    }

    @GetMapping("/authorities")
    public Iterable<Authority> getAuthorities(@RequestParam String username) {
        return authorityRepository.findAllByPersonUsername(username);
    }

    @GetMapping("{username}/authorities")
    public Iterable<Authority> addToAuthorities(@PathVariable String username, @RequestParam String authority) {
        // TODO
        return null;
    }
}
