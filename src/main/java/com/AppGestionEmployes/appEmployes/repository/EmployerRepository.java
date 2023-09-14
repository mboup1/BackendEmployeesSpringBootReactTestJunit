package com.AppGestionEmployes.appEmployes.repository;

import com.AppGestionEmployes.appEmployes.model.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Long> {
}
