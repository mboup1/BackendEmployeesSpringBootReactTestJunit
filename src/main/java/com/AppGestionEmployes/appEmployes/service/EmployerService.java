package com.AppGestionEmployes.appEmployes.service;

import com.AppGestionEmployes.appEmployes.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import com.AppGestionEmployes.appEmployes.repository.EmployerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    public List<Employer> getEmployers() {
        List<Employer> employers = new ArrayList<>();
        employerRepository.findAll().forEach(employer -> {
            employers.add(employer);
        });
        return employers;
    }

    public Employer getEmployer(long id) { return employerRepository.findById(id).orElse(null); }

    public void deleteEmployer(long id) { employerRepository.deleteById(id);  }

    public Employer addEmployer(Employer employer) {
        employerRepository.save(employer);
        return employer;
    }

    public Employer updateEmployer(Employer employer, long id) {
        employerRepository.save(employer);
        return employer;

    }
}
