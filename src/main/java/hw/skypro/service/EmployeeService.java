package hw.skypro.service;

import hw.skypro.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {


    Employee addEmployee(String firstName, String lastName, int salary, int department);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    List<Employee> getAllEmployee();

    List<Employee> fillForTest();


}
