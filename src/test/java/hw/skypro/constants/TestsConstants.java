package hw.skypro.constants;

import hw.skypro.model.Employee;

import java.util.List;

public class TestsConstants {
    public static final String ILLEGAL_CHARS_NAME = "Ivan123";
    public static final String CORRECT_FIRSTNAME = "Ivan";
    public static final String CORRECT_FIRSTNAME_TWO = "Ivana";
    public static final String CORRECT_LASTNAME = "Ivanov";
    public static final String CORRECT_LASTNAME_TWO = "Ivanova";
    public static final String CORRECT_FIRSTNAME_TREE = "Ivanovq";
    public static final String CORRECT_LASTNAME_TREE = "Ivanovq";


    public static final int SALARY_ONE = 100;
    public static final int SALARY_TREE = 300;
    public static final int SALARY_TWO = 200;
    public static final int DEPARTMENT_ONE = 1;
    public static final int DEPARTMENT_TREE = 1;
    public static final int DEPARTMENT_TWO = 2;


    public static final Employee EMPLOYEE_1_DEP1_MIN_SALARY =
            new Employee(CORRECT_FIRSTNAME,CORRECT_LASTNAME, SALARY_ONE, DEPARTMENT_ONE);
    public static final Employee EMPLOYEE_3_DEP3 =
            new Employee(CORRECT_FIRSTNAME_TWO,CORRECT_LASTNAME_TWO,SALARY_TWO,DEPARTMENT_TWO);
    public static final Employee EMPLOYEE_2_DEP1_MAX_SALARY =
            new Employee(CORRECT_FIRSTNAME_TREE,CORRECT_LASTNAME_TREE,SALARY_TREE,DEPARTMENT_TREE);
    public static final List<Employee> LIST_EMPLOYEE_SIZE2_DEP1 = List.of(EMPLOYEE_1_DEP1_MIN_SALARY, EMPLOYEE_2_DEP1_MAX_SALARY);
    public static final List<Employee> LIST_EMPLOYEE_SIZE3 = List.of(EMPLOYEE_1_DEP1_MIN_SALARY, EMPLOYEE_3_DEP3, EMPLOYEE_2_DEP1_MAX_SALARY);




}
