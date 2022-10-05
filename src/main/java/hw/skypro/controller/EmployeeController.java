package hw.skypro.controller;


import hw.skypro.Employee;
import hw.skypro.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.employeeService.fillForTest();
    }

    @GetMapping()
    public String greetings() {
        return "OK";
    }

    @GetMapping(path = "/fillForTest")
    public List<Employee> fillForTest() {
        return employeeService.fillForTest();
    }

    @GetMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int salary, @RequestParam int department) {
        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/remove")
    @ResponseStatus(HttpStatus.OK)
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")
    @ResponseStatus(HttpStatus.OK)
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "/getall")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

}
