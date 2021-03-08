package com.example.payroll.employee;

import java.util.List;
import java.util.stream.Collectors;

import com.example.payroll.employee.Employee;
import com.example.payroll.employee.EmployeeModelAssembler;
import com.example.payroll.employee.EmployeeNotFoundException;
import com.example.payroll.employee.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeModelAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    //Get All
    @GetMapping("api/employees")
    CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }


    //Create
    @PostMapping("api/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {

        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    //Get one
    @GetMapping("api/employees/{username}")
    EntityModel<Employee> one(@PathVariable String username) {

        Employee employee = repository.findById(username) //
                .orElseThrow(() -> new EmployeeNotFoundException(username));

        return assembler.toModel(employee);
    }


    //Update
    @PutMapping("api/employees/{username}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable String username) {

        Employee updatedEmployee = repository.findById(username) //
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setPassword(newEmployee.getPassword());
                    employee.setRole(newEmployee.getRole());
                    employee.setSalary(newEmployee.getSalary());
                    employee.setPay_cycle(newEmployee.getPay_cycle());
                    employee.setPay(newEmployee.getPay());
                    return repository.save(employee);
                }) //
                .orElseGet(() -> {
                    newEmployee.setUsername(username);
                    return repository.save(newEmployee);
                });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("api/employees/{username}")
    ResponseEntity<?> deleteEmployee(@PathVariable String username) {

        repository.deleteById(username);

        return ResponseEntity.noContent().build();
    }
}
