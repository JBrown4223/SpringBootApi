package com.example.payroll.employee;

import com.example.payroll.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
interface EmployeeRepository extends JpaRepository<Employee, String> {

}

