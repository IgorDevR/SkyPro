package hw.skypro.service;

import hw.skypro.exceptions.RequestExceedsNumberOfDataException;
import hw.skypro.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService service;
    @InjectMocks
    private ExaminerServiceImpl out;

    List<Question> testQuestions = new ArrayList<>();

    @BeforeEach
    void init() {

        testQuestions.add(new Question("TestA1", "TestQ1"));
        testQuestions.add(new Question("TestA2", "TestQ2"));
        testQuestions.add(new Question("TestA3", "TestQ3"));
        testQuestions.add(new Question("TestA4", "TestQ4"));
        testQuestions.add(new Question("TestA5", "TestQ5"));

        when(service.getAll()).thenReturn(testQuestions);

    }


    @Test
    void getQuestions() {

        when(service.getRandomQuestion()).thenAnswer((i) -> testQuestions.stream()
                .skip(ThreadLocalRandom.current().nextInt(testQuestions.size()))
                .findFirst()
                .orElseThrow());

        assertThat(Set.copyOf(out.getQuestions(0)).size()).isEqualTo(0);
        assertThat(Set.copyOf(out.getQuestions(5)).size()).isEqualTo(5);


    }

    @Test
    void getQuestionsException() {

        assertThatExceptionOfType(RequestExceedsNumberOfDataException.class)
                .isThrownBy(() -> out.getQuestions(7));
    }

}