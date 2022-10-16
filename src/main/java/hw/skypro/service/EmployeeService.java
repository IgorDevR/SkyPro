package hw.skypro.service;

import hw.skypro.exception.EmployeeAlreadyAddedException;
import hw.skypro.exception.EmployeeNotFoundException;
import hw.skypro.exception.EmployeeStorageIsFullException;
import hw.skypro.model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {



    private  int LIMIT = 10;
    public final Map<String, Employee> employees = new HashMap<>();
    private final InputValidationService inputValidation;

    private String getKey(String firstName, String lastName) {
        return firstName + "|" + lastName;
    }

    public EmployeeService(InputValidationService inputValidation) {
        this.inputValidation = inputValidation;
    }

    public Employee addEmployee(String firstName, String lastName, double salary, int department) {

        Employee emp = new Employee(inputValidation.checkFirstNameValid(firstName),
                inputValidation.checkLastNameValid(lastName),
                salary, department);

        String key = getKey(firstName, lastName);

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(key, emp);
            return emp;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee removeEmployee(String firstName, String lastName) {

        String key = getKey(firstName, lastName);

        if (employees.containsKey(key)) {
            Employee emp = employees.get(key);
            employees.remove(key);
            return emp;
        }
        throw new EmployeeNotFoundException();
    }


    public Employee findEmployee(String firstName, String lastName) {

        String key = getKey(firstName, lastName);

        if (employees.containsKey(key)) {
            Employee emp = employees.get(key);
            return emp;
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getAllEmployee() {
        return employees.values().stream().toList();

    }

    public void setLIMIT(int LIMIT) {
        this.LIMIT = LIMIT;
    }
}

