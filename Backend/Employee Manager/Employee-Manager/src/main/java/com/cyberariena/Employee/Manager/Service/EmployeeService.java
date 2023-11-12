package com.cyberariena.Employee.Manager.Service;

import com.cyberariena.Employee.Manager.Exception.UserNotFoundException;
import com.cyberariena.Employee.Manager.Model.Employee;
import com.cyberariena.Employee.Manager.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository empRepo;

    public Iterable<Employee> getAllEmployees(){
        return (List<Employee>) empRepo.findAll();
    }
    public Employee SaveEmployee(Employee employee){
        return empRepo.save(employee);
    }
    public  Employee getEmployeeById (Long id){
        Optional <Employee> employeeOptional = empRepo.findById(id);
        return employeeOptional.orElseThrow(() -> new UserNotFoundException("User by id "+id+" was not found"));
    }
    public Employee updateEmployee(Employee employee){
        return empRepo.save(employee);
    }
    public void deleteEmployee(Long id){
         empRepo.deleteById(id);
    }
}
