package com.cyberariena.Employee.Manager.Repository;

import com.cyberariena.Employee.Manager.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
