package mvc;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private Map<Integer, Employee> employees = new HashMap<>();
    private static Integer ID = 1;

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        //условно максимум который можно добавить это 10. если больше то выбрасывается исключение
        if (employees.size() > 10) {
            throw new EmployeeArrayIsFullException();
        }

        Employee emp = new Employee(firstName, lastName);
        employees.put(ID, emp);
        return employees.get(ID++);

    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {

        for (Map.Entry<Integer, Employee> emp : employees.entrySet()) {
            var q = emp;
            if (emp.getValue().getFirstName().equals(firstName) && emp.getValue().getLastName().equals(lastName)) {
                employees.remove(emp.getKey());
                return emp.getValue();
            }

        }

        throw new EmployeeAlreadyAddedException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        for (Map.Entry<Integer, Employee> emp : employees.entrySet()) {
            if (emp.getValue().getFirstName().equals(firstName) && emp.getValue().getLastName().equals(lastName)) {
                return emp.getValue();
            }
        }
        throw new EmployeeNotFoundException();

    }

    @Override
    public Map<Integer, Employee> getAllEmployee() {
        return employees;
    }

    class EmployeeArrayIsFullException extends RuntimeException {
        @Override
        public String toString() {
            return "Массив переполнен";
        }
    }

    class EmployeeAlreadyAddedException extends RuntimeException {
        @Override
        public String toString() {
            return "Такой сотрудник уже имеется";
        }
    }

    class EmployeeNotFoundException extends RuntimeException {
        @Override
        public String toString() {
            return "Сотрудник не найден";
        }
    }


}

