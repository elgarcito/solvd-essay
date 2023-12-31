package com.solvd.essay.service;

import com.solvd.essay.domain.BatchInfo;
import com.solvd.essay.domain.Employee;
import com.solvd.essay.persistence.impl.AbstracDao;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final AbstracDao<Employee> employeeImpl;

    public EmployeeService(AbstracDao<Employee> employeeAbstracDao){
        this.employeeImpl =employeeAbstracDao;
    }

    public void create(Employee employee){
        try {
            employeeImpl.create(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Employee> findAll() throws SQLException {
            return employeeImpl.getAll();
    }

    public Employee findOne(Long id) throws SQLException {
        Employee result= employeeImpl.findById(id);
        if (result==null){
            return new Employee();
        }
        return result;
    }

    public void deleteOne(Long id) throws SQLException {
        employeeImpl.deleteById(id);
    }

    public void deleteEntity(Employee employee){
        employeeImpl.delete(employee);
    }

    public void updateEntity(Employee employee){
        try {
            employeeImpl.update(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
