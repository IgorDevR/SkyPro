package hw.skypro.service;

import hw.skypro.exceptions.RequestExceedsNumberOfDataException;
import hw.skypro.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

//    @Mock
    private JavaQuestionService service;
//    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void getQuestions() {

        service = new JavaQuestionService();
        service.add("TestA1", "TestQ1");
        service.add("TestA2", "TestQ2");
        service.add("TestA3", "TestQ3");
        service.add("TestA4", "TestQ4");
        service.add("TestA5", "TestQ5");

        out = new ExaminerServiceImpl(service);

        List<Question> list = new ArrayList<>();
        var q = out.getQuestions(5).stream().toList();
        list = out.getQuestions(5).stream().toList();

        assertThat(list.get(0) != list.get(1) && list.get(1) != list.get(2)
        && list.get(2) != list.get(3) &&list.get(3) != list.get(4)).isTrue();

        List<Question> list2 = new ArrayList<>();
        list2 = out.getQuestions(5).stream().toList();

        assertThat(list.equals(list2)).isFalse();

        assertThatExceptionOfType(RequestExceedsNumberOfDataException.class)
                .isThrownBy(() -> out.getQuestions(7));
    }


}