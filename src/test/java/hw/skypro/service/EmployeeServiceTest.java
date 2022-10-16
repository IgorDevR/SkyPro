package hw.skypro.service;


import hw.skypro.exception.EmployeeAlreadyAddedException;
import hw.skypro.exception.EmployeeNotFoundException;
import hw.skypro.exception.EmployeeStorageIsFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static hw.skypro.constants.TestsConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {


    @Mock
    private InputValidationService inputValidationService;
    @InjectMocks
    private EmployeeService out;

    @BeforeEach
    void addEmployeeValidForTests() {

        when(inputValidationService.checkFirstNameValid(CORRECT_FIRSTNAME)).thenReturn(CORRECT_FIRSTNAME);
        when(inputValidationService.checkLastNameValid(CORRECT_LASTNAME)).thenReturn(CORRECT_LASTNAME);
        assertThat(out.addEmployee(CORRECT_FIRSTNAME, CORRECT_LASTNAME, SALARY_ONE, DEPARTMENT_ONE)).isEqualTo(EMPLOYEE_1_DEP1_MIN_SALARY);

        when(inputValidationService.checkFirstNameValid(CORRECT_FIRSTNAME_TWO)).thenReturn(CORRECT_FIRSTNAME_TWO);
        when(inputValidationService.checkLastNameValid(CORRECT_LASTNAME_TWO)).thenReturn(CORRECT_LASTNAME_TWO);
        assertThat(out.addEmployee(CORRECT_FIRSTNAME_TWO, CORRECT_LASTNAME_TWO, SALARY_TWO, DEPARTMENT_TWO)).isEqualTo(EMPLOYEE_3_DEP3);

    }

    @Test
    void addEmployeeValidTest() {

        assertThat(out.getAllEmployee()).size().isEqualTo(2);

        when(inputValidationService.checkFirstNameValid(CORRECT_FIRSTNAME_TREE)).thenReturn(CORRECT_LASTNAME_TREE);
        when(inputValidationService.checkLastNameValid(CORRECT_FIRSTNAME_TREE)).thenReturn(CORRECT_LASTNAME_TREE);
        assertThat(out.addEmployee(CORRECT_FIRSTNAME_TREE, CORRECT_LASTNAME_TREE, SALARY_TREE, DEPARTMENT_TREE)).isEqualTo(EMPLOYEE_2_DEP1_MAX_SALARY);

        assertThat(out.getAllEmployee()).size().isEqualTo(3);
    }

    @Test
    void addEmployeeAlreadyExceptionTest() {

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> out.addEmployee(CORRECT_FIRSTNAME_TWO, CORRECT_LASTNAME_TWO, SALARY_TWO, DEPARTMENT_TWO));

    }

    @Test
    void addEmployeeStorageIsFullExceptionTest() {

        out.setLIMIT(2);
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> out.addEmployee(CORRECT_FIRSTNAME_TREE, CORRECT_LASTNAME_TREE, SALARY_TREE, DEPARTMENT_TREE));
    }


    @Test
    void removeEmployeeTest() {

        assertThat(out.getAllEmployee()).size().isEqualTo(2);
        assertThat(out.removeEmployee(CORRECT_FIRSTNAME, CORRECT_LASTNAME)).isEqualTo(EMPLOYEE_1_DEP1_MIN_SALARY);
        assertThat(out.getAllEmployee()).size().isEqualTo(1);
    }

    @Test
    void removeEmployeeNotFoundExceptionTest() {

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.removeEmployee(CORRECT_FIRSTNAME_TREE + 'q', CORRECT_LASTNAME_TREE));

    }


    @Test
    void findEmployeeTest() {

        assertThat(out.findEmployee(CORRECT_FIRSTNAME, CORRECT_LASTNAME)).isEqualTo(EMPLOYEE_1_DEP1_MIN_SALARY);
    }

    @Test
    void findEmployeeEmployeeNotFoundExceptionTest() {

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.findEmployee(CORRECT_FIRSTNAME_TREE + 'q', CORRECT_LASTNAME_TREE));
    }


    @Test
    void getAllEmployeeTest() {
        assertThat(out.getAllEmployee()).size().isEqualTo(2);
        assertThat(out.getAllEmployee()).containsExactlyInAnyOrder(EMPLOYEE_1_DEP1_MIN_SALARY, EMPLOYEE_3_DEP3);
    }

    @Test
    void getAllEmployeeClearListTest() {

        assertThat(out.getAllEmployee()).size().isEqualTo(2);
        assertThat(out.removeEmployee(CORRECT_FIRSTNAME, CORRECT_LASTNAME)).isEqualTo(EMPLOYEE_1_DEP1_MIN_SALARY);
        assertThat(out.removeEmployee(CORRECT_FIRSTNAME_TWO, CORRECT_LASTNAME_TWO)).isEqualTo(EMPLOYEE_3_DEP3);
        assertThat(out.getAllEmployee()).size().isEqualTo(0);

        assertThat(out.getAllEmployee()).isEmpty();
    }
}