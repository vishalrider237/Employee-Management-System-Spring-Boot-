package com.agularspringboot.controller;

import com.agularspringboot.model.Employee;
import com.agularspringboot.service.EmployeeService;
import com.agularspringboot.util.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("angular/spring-boot")
public class Controller {

   @Autowired
   private EmployeeService employeeService;

   @MutationMapping(name = "addEmployee")
   public MessageDto addEmployee(@Argument String name, @Argument String email, @Argument String phone_no,@Argument String address){
       return employeeService.createEmployee(name,email,phone_no,address);
   }

   @QueryMapping(name = "getAllEmployee")
    public List<Employee> getAllEmployee(){
       return employeeService.getAllEmployee();
   }
   @QueryMapping(name = "getEmployeeBasedOnId")
    public Employee getEmployeeBasedOnId(@Argument Long id){
       return employeeService.getEmployeeBasedOnId(id);
   }
   @MutationMapping("deleteEmployee")
    public MessageDto deleteEmployee(@Argument Long id){
       return employeeService.deleteEmployee(id);
   }
   @MutationMapping("updateEmployee")
    public MessageDto updateEmployee(@Argument Long id, @Argument String name, @Argument String email, @Argument String phone_no,@Argument String address){
       return employeeService.updateExistingEmployee(id,name,email,phone_no,address);
   }
}
