package com.AppGestionEmployes.appEmployes.controller;

import com.AppGestionEmployes.appEmployes.model.Employer;
import com.AppGestionEmployes.appEmployes.service.EmployerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployerController.class)
class EmployerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployerService employerService;
    @Test
    void getEmployer() {
        // arrange
        Long userId = 1L;
        Employer employer = new Employer();
        employer.setId(userId);
        employer.setFirstName("John Doe");
        Mockito.when(employerService.getEmployer(userId)).thenReturn(employer);

        // act
        Employer result = employerService.getEmployer(userId);

        // assert
        assertNotNull(result, "The returned user should not be null");
        assertEquals(userId, result.getId(), "User ID should match");
        assertEquals("John Doe", result.getFirstName(), "User name should match");
    }

    @Test
    void addEmployer() {
        // arrange
        Employer newEmployer = new Employer();
        newEmployer.setName("Doe");
        newEmployer.setFirstName("John");
        newEmployer.setEmail("john.doe@example.com");

        Mockito.when(employerService.addEmployer(Mockito.any(Employer.class))).thenReturn(newEmployer);

        // act
        Employer result = employerService.addEmployer(newEmployer);

        // assert
        assertNotNull(result, "The added employer should not be null");
        assertEquals("Doe", result.getName(), "Employer name should match");
        assertEquals("John", result.getFirstName(), "Employer first name should match");
        assertEquals("john.doe@example.com", result.getEmail(), "Employer email should match");

        // Verify that the method was called with the correct parameters
        Mockito.verify(employerService).addEmployer(newEmployer);
    }

    @Test
    void deleteEmployer() {
        // arrange
        Long userId = 1L;
        Mockito.doNothing().when(employerService).deleteEmployer(userId);
        // act
        employerService.deleteEmployer(userId);
        // assert
        Mockito.verify(employerService).deleteEmployer(userId);
    }

    @Test
    void updateEmployer() {
        // arrange
        Long employerId = 1L;
        Employer existingEmployer = new Employer();
        existingEmployer.setId(employerId);
        existingEmployer.setName("Old Name");
        existingEmployer.setFirstName("Old First Name");
        existingEmployer.setEmail("old.email@example.com");

        Employer updatedEmployer = new Employer();
        updatedEmployer.setId(employerId);
        updatedEmployer.setName("New Name");
        updatedEmployer.setFirstName("New First Name");
        updatedEmployer.setEmail("new.email@example.com");

        Mockito.when(employerService.updateEmployer(Mockito.any(Employer.class), Mockito.eq(employerId))).thenReturn(updatedEmployer);

        // act
        Employer result = employerService.updateEmployer(updatedEmployer, employerId);

        // assert
        assertNotNull(result, "The updated employer should not be null");
        assertEquals("New Name", result.getName(), "Employer name should match the updated value");
        assertEquals("New First Name", result.getFirstName(), "Employer first name should match the updated value");
        assertEquals("new.email@example.com", result.getEmail(), "Employer email should match the updated value");

        // Verify that the method was called with the correct parameters
        Mockito.verify(employerService).updateEmployer(updatedEmployer, employerId);
    }


}