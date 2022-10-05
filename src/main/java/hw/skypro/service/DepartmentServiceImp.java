package hw.skypro.service;

import hw.skypro.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImp implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImp(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeMaxSalaryFromDepartment(int department) {

        return employeeService.getAllEmployee().stream().filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(employee -> employee.getSalary())).orElse(null);
    }

    @Override
    public Employee findEmployeeMinSalaryFromDepartment(int department) {
        return employeeService.getAllEmployee().stream().filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(employee -> employee.getSalary())).orElse(null);
    }

    @Override
    public List<Employee> findAllEmployeesFromDepartment(int department) {
        return employeeService.getAllEmployee().stream()
                .filter(e -> e.getDepartment() == department).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findAllEmployees() {
        return employeeService.getAllEmployee().stream()
                .collect(Collectors.groupingBy((Employee::getDepartment)));
    }
}
