package mvc;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.addEmployee(firstName, lastName);

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
    public Map<Integer, Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

}
