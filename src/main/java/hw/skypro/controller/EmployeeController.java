package hw.skypro.controller;


import hw.skypro.model.Employee;
import hw.skypro.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String greetings() {
        return "OK";
    }

    @GetMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam("departmentId") int department) {
        Employee result = employeeService.addEmployee(firstName, lastName, salary, department);
        return result != null ? result :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expectation Failed (CODE 400 Bad Request)");
    }

    @GetMapping(path = "/remove")
    @ResponseStatus(HttpStatus.OK)
    public Object removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.removeEmployee(firstName, lastName);
        return result != null ? result :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expectation Failed (CODE 400 Bad Request)");
    }

    @GetMapping(path = "/find")
    @ResponseStatus(HttpStatus.OK)
    public Object findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.findEmployee(firstName, lastName);
        return result != null ? result :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expectation Failed (CODE 400 Bad Request)");
    }

    @GetMapping(path = "/getall")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

}
