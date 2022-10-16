package hw.skypro.service;

import hw.skypro.exception.EmployeeNotFoundException;
import hw.skypro.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findEmployeeMaxSalaryFromDepartment(int department) {

        return employeeService.getAllEmployee().stream().filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(employee -> employee.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee findEmployeeMinSalaryFromDepartment(int department) {
        return employeeService.getAllEmployee().stream().filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(employee -> employee.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findAllEmployeesFromDepartment(int department) {
        return employeeService.getAllEmployee().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> findAllEmployees() {
        return employeeService.getAllEmployee().stream()
                .collect(Collectors.groupingBy((Employee::getDepartment)));
    }
}
