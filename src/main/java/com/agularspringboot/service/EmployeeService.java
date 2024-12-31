package com.agularspringboot.service;

import com.agularspringboot.model.Employee;
import com.agularspringboot.repository.EmployeeRepository;
import com.agularspringboot.util.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository crudRepository;

    public MessageDto createEmployee(String name, String email, String phoneNo, String address) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setAddress(address);
        employee.setPhone_no(phoneNo);
        crudRepository.save(employee);
        return new MessageDto("Emplyee Created Successfully!!",200);
    }

    public List<Employee> getAllEmployee() {
        return this.crudRepository.findAll();
    }

    public Employee getEmployeeBasedOnId(Long id) {
        return this.crudRepository.findById(id).get();
    }

    public MessageDto deleteEmployee(Long id) {
        Optional<Employee>existingEmployee=this.crudRepository.findById(id);
        if (existingEmployee.isPresent()) {
            crudRepository.delete(existingEmployee.get());
            return new MessageDto("Employee Deleted Successfully!!",200);
        }
        return new MessageDto("Employee Not Found!!",404);
    }

    public MessageDto updateExistingEmployee(Long id, String name, String email, String phoneNo, String address) {
        Optional<Employee>existingEmployee=this.crudRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(name);
            employee.setEmail(email);
            employee.setPhone_no(phoneNo);
            employee.setAddress(address);
            crudRepository.save(employee);
            return new MessageDto("Employee Updated Successfully!!",200);
        }
        return new MessageDto("Employee Not Found!!",404);
    }
}
