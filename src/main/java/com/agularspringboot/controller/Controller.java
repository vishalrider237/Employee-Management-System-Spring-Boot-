package com.agularspringboot.controller;

import com.agularspringboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("angular/spring-boot")
public class Controller {
    @Autowired
    private CrudRepository repository;
    @GetMapping("/getAll")
    public ResponseEntity<?>getAllEmployee(){
        return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?>addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(this.repository.save(employee), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteEmployee(@PathVariable long id){
        this.repository.deleteById(id);
        return new ResponseEntity<>("Data Deleted", HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?>getEmployeeById(@PathVariable long id){
        Optional employee=  this.repository.findById(id);
        if (employee.isPresent()){
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Employee Not Found with id:"+id, HttpStatus.OK);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateEmployee(@PathVariable long id,@RequestBody Employee employee) throws Throwable {
        Employee employee1= (Employee) this.repository.findById(id).orElseThrow(()->new RuntimeException("Enployee Not Found with id: " + id));
        employee1.setName(employee.getName());
        employee1.setEmail(employee.getEmail());
        employee1.setPhone_no(employee.getPhone_no());
        employee1.setAddress(employee.getAddress());
        this.repository.save(employee1);
        return  new ResponseEntity<>("Employee Updated Successfully",HttpStatus.OK);
    }
}
