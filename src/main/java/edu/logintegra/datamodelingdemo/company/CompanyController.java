package edu.logintegra.datamodelingdemo.company;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/list")
    public Iterable<Company> list() {
        return companyRepository.findAll();
    }

    @GetMapping("/show")
    public String getName(@RequestParam Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()) {
            return optionalCompany.get().getName();
        } else {
            return null;
        }
    }
}
