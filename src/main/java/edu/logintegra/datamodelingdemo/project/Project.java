package edu.logintegra.datamodelingdemo.project;

import edu.logintegra.datamodelingdemo.person.Person;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Person creator;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public Project(Person creator, String name, String code) {
        this.creator = creator;
        this.name = name;
        this.code = code;
    }

    public Project() {
    }
}
