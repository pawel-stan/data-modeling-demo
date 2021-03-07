package edu.logintegra.datamodelingdemo.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.logintegra.datamodelingdemo.person.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "company")
    @JsonIgnoreProperties("company")
    private Set<Person> people;

    public Company(String name) {
        this.name = name;
    }

    private String name;

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<Person> getPeople() {
        return people;
    }
}
