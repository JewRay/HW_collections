package com.example.hwcollections.service;

import com.example.hwcollections.exceptions.EmployeeAlreadyAddedException;
import com.example.hwcollections.exceptions.EmployeeNotFoundException;
import com.example.hwcollections.exceptions.EmployeeStorageIsFullException;
import com.example.hwcollections.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeService {
    private final List<Employee> employees;
    private static final int MAX_EMPLOYEES = 10;

    public EmployeeService() {
        employees = new ArrayList<>();
    }

    public void addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }

        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException();
            }
        }

        employees.add(new Employee(firstName, lastName));
    }

    public void removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                employees.remove(employee);
                return;
            }
        }

        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }

        throw new EmployeeNotFoundException();
    }

    public List<Employee> getAllEmployees() {
        return Collections.unmodifiableList(employees);
    }
}