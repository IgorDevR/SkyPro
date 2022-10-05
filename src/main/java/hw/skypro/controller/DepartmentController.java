package hw.skypro.controller;

import hw.skypro.Employee;
import hw.skypro.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findEmployeeMaxSalaryFromDepartment(@RequestParam("departmentId") int department){
        return departmentService.findEmployeeMaxSalaryFromDepartment(department);
    }
    @GetMapping("/min-salary")
    public Employee findEmployeeMinSalaryFromDepartment(@RequestParam("departmentId") int department){
        return departmentService.findEmployeeMinSalaryFromDepartment(department);
    }
    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> findAllEmployeesFromDepartment(@RequestParam("departmentId") int department){
        return departmentService.findAllEmployeesFromDepartment(department);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> findAllEmployees(){
        return departmentService.findAllEmployees();
    }

}
