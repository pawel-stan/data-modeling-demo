package edu.logintegra.datamodelingdemo.project;

import edu.logintegra.datamodelingdemo.person.Person;
import edu.logintegra.datamodelingdemo.person.PersonRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final PersonRepository personRepository;
    private final ProjectRepository projectRepository;

    public ProjectController(PersonRepository personRepository, ProjectRepository projectRepository) {
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
    }

    @PostMapping("/save")
    public Project save(@RequestParam String name, @RequestParam String code) {
        Optional<Person> optPerson = personRepository.findByUsernameAndEnabled("wsb-10", true);

        if (optPerson.isEmpty()) {
            throw new InvalidParameterException("No user found");
        }

        Person person = optPerson.get();
        Project project = new Project(person, name, code);

        return projectRepository.save(project);
    }
}
