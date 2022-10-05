package hw.skypro.service;

import hw.skypro.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

     Employee findEmployeeMaxSalaryFromDepartment(int department);
     Employee findEmployeeMinSalaryFromDepartment(int department);
     List<Employee> findAllEmployeesFromDepartment(int department);
     Map<Integer, List<Employee>> findAllEmployees();

}
