package hw.skypro.service;

import hw.skypro.Employee;
import hw.skypro.InputValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImp implements EmployeeService {

    public List<Employee> employees = new ArrayList<>();
    private final InputValidation inputValidation;

    public EmployeeServiceImp(InputValidation inputValidation) {
        this.inputValidation = inputValidation;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {

//        //условно максимум который можно добавить это 10. если больше то выбрасывается исключение
//        if (employees.size() > 10) {
//            throw new EmployeeArrayIsFullException();
//        }
        Employee emp = new Employee(inputValidation.checkValid(firstName), inputValidation.checkValid(lastName), salary, department);
        employees.add(emp);
        return emp;

    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {

      for(Employee emp : employees){
          if(emp.getFirstName().equals(inputValidation.checkValid(firstName))&& emp.getLastName()
                  .equals(inputValidation.checkValid(lastName))){
              employees.remove(emp);
              return emp;
          }
      }
        throw new EmployeeAlreadyAddedException();
    }


    @Override
    public Employee findEmployee(String firstName, String lastName) {

        return employees.stream().filter(e -> e.getFirstName()
                .equals(inputValidation.checkValid(firstName)))
                .filter(e -> e.getLastName().equals(inputValidation.checkValid(lastName)))
                .findFirst().orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public List<Employee> getAllEmployee() {
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

    public List<Employee> fillForTest() {

        Employee emp1D1 = new Employee("Ivan1", "Ivanov1", 500, 1);
        Employee emp2D1 = new Employee("Ivan2", "Ivanov2", 600, 1);
        Employee emp3D1 = new Employee("Ivan3", "Ivanov3", 700, 1);

        Employee emp1D2 = new Employee("Ivan4", "Ivanov4", 1000, 2);
        Employee emp2D2 = new Employee("Ivan5", "Ivanov5", 1500, 2);
        Employee emp3D2 = new Employee("Ivan6", "Ivanov6", 2000, 2);

        Employee emp1D5 = new Employee("Ivan6", "Ivanov6", 3000, 5);
        Employee emp2D5 = new Employee("Ivan7", "Ivanov7", 3500, 5);
        Employee emp3D5 = new Employee("Ivan8", "Ivanov8", 4000, 5);

        employees.add(emp1D1);
        employees.add(emp2D1);
        employees.add(emp3D1);

        employees.add(emp1D2);
        employees.add(emp2D2);
        employees.add(emp3D2);

        employees.add(emp1D5);
        employees.add(emp2D5);
        employees.add(emp3D5);

        return employees;
    }

}

