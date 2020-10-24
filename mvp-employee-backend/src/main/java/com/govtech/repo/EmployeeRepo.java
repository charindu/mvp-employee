package com.govtech.repo;

import com.govtech.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Employee findByEmployeeId( String employeeId);

    Page<Employee> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageRequest);

    boolean existsById( Long id);

}
