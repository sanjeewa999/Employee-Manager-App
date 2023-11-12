package com.cyberariena.Employee.Manager.Controller;

import com.cyberariena.Employee.Manager.Model.Employee;
import com.cyberariena.Employee.Manager.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = (List<Employee>) employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee emp = employeeService.SaveEmployee(employee);
        return new ResponseEntity<>(emp,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id,@RequestBody Employee updateEmployee){
        Employee exsistingEmployee = employeeService.getEmployeeById(id);
        if (exsistingEmployee != null){
            exsistingEmployee.setName(updateEmployee.getName());
            exsistingEmployee.setEmail(updateEmployee.getEmail());
            exsistingEmployee.setPhone(updateEmployee.getPhone());
            exsistingEmployee.setJobTitle(updateEmployee.getJobTitle());

            Employee updateEmployeeObj = employeeService.updateEmployee(exsistingEmployee);
            return ResponseEntity.ok(updateEmployeeObj);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.getEmployeeById(id);
            return ResponseEntity.ok("Employee is  deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while deleting Employee: " + e.getMessage());
        }
    }

}
