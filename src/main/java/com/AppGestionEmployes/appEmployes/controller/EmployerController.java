package com.AppGestionEmployes.appEmployes.controller;

import com.AppGestionEmployes.appEmployes.model.Employer;
import com.AppGestionEmployes.appEmployes.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class EmployerController {
    @Autowired
    private EmployerService employerService;


    //test
    @RequestMapping(method = RequestMethod.GET, value = "/employers")
    public List<Employer> getEmployers(){return employerService.getEmployers();}

    @RequestMapping(method = RequestMethod.GET, value = "/employer/{id}")
    public Employer getEmployer(@PathVariable long id){return employerService.getEmployer(id); }

//    @RequestMapping(method =  RequestMethod.POST, value = "/employer/{id}")
//    public void addEmployer(@RequestBody Employer employer){employerService.addEmployer(employer);}
    @PostMapping("/employer/{id}")
    public ResponseEntity<Object> addEmployer(@RequestBody Employer employer) {
        // Vérification des champs vides
        if (employer.getFirstName().isBlank() || employer.getName().isBlank() || employer.getEmail().isBlank()) {
            return new ResponseEntity<>("Tous les champs doivent être remplis.", HttpStatus.BAD_REQUEST);
        }
        Employer createdEmployer = employerService.addEmployer(employer);
        return new ResponseEntity<>(createdEmployer, HttpStatus.CREATED);
    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "/employer/{id}")
//    public void deleteEmployer(@PathVariable long id){employerService.deleteEmployer(id);}
    @DeleteMapping("/employer/{id}")
    public ResponseEntity<String> deleteEmployer(@PathVariable long id) {
        Employer deletedEmployee = employerService.getEmployer(id);

        if (deletedEmployee != null) {
            employerService.deleteEmployer(id);
            return new ResponseEntity<>("L'employé avec Id " + id + " a été supprimé avec succès", HttpStatus.OK);
        } else {
            // If the employer with the specified ID is not found, return a 404 Not Found status.
            return new ResponseEntity<>("L'employé avec Id " + id + " n'est pas trouvé", HttpStatus.NOT_FOUND);
        }
    }


//    @RequestMapping(method =  RequestMethod.PUT, value = "/employer/{id}")
//    public void updateEmployer(@RequestBody Employer employer, @PathVariable long id){
//        employerService.updateEmployer(employer, id);
//    }

    @PutMapping("/employer/{id}")
    public ResponseEntity<Object> updateEmployer(@RequestBody Employer employer, @PathVariable long id) {
        try {
            // Check if the employer with the specified ID exists
            Employer existingEmployee = employerService.getEmployer(employer.getId());

            if (existingEmployee != null) {

                if (id==employer.getId()) {
                // Update the existing employer
                Employer updatedEmployee = employerService.updateEmployer(employer, id);
                System.out.println("updatedEmployee : "+updatedEmployee);

                return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("L'employé avec ID " + employer.getId() + " ne correspond pas avec Id de l'url "+id, HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("L'employé avec ID " + id + " n'existe pas. Vous pouvez créer un nouvel employé avec ce ID.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la mise à jour de l'employé avec ID " + id + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
