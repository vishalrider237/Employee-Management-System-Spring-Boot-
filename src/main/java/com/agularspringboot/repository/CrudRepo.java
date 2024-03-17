package com.agularspringboot.repository;

import com.agularspringboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepo extends JpaRepository<Employee, Long> {
}
