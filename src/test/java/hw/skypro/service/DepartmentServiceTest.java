package hw.skypro.service;

import hw.skypro.exception.EmployeeNotFoundException;
import hw.skypro.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;

import static hw.skypro.constants.TestsConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService out;

    @BeforeEach
    public void addEmployeeForTests(){
        when(employeeService.getAllEmployee()).thenReturn(LIST_EMPLOYEE_SIZE3);
    }

    @Test
    void findEmployeeMaxSalaryFromDepartmentTest() {
        assertThat(out.findEmployeeMaxSalaryFromDepartment(1)).isEqualTo(EMPLOYEE_2_DEP1_MAX_SALARY);

    }
    @Test
    void findEmployeeMaxSalaryExceptionTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(()-> out.findEmployeeMaxSalaryFromDepartment(3));
    }

    @Test
    void findEmployeeMinSalaryFromDepartmentTest() {
        assertThat(out.findEmployeeMinSalaryFromDepartment(1)).isEqualTo(EMPLOYEE_1_DEP1_MIN_SALARY);
    }
    @Test
    void findEmployeeMinSalaryExceptionTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(()-> out.findEmployeeMinSalaryFromDepartment(3));
    }
    @Test
    void findAllEmployeesFromDepartmentTest() {
        assertThat(out.findAllEmployeesFromDepartment(1)).containsExactlyInAnyOrderElementsOf(LIST_EMPLOYEE_SIZE2_DEP1);
        assertThat(out.findAllEmployeesFromDepartment(2)).containsExactlyInAnyOrder(EMPLOYEE_3_DEP3);
    }

    @Test
    void findAllEmployeesTest() {
        var mapCollect = LIST_EMPLOYEE_SIZE3.stream().collect(Collectors.groupingBy((Employee::getDepartment)));
        assertThat(out.findAllEmployees()).containsExactlyInAnyOrderEntriesOf(mapCollect);
    }


}