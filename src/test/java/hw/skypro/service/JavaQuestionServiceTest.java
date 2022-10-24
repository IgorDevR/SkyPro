package hw.skypro.service;

import hw.skypro.exceptions.QuestionNotFoundExceptions;
import hw.skypro.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    QuestionService out;
    Question q1;

    @BeforeEach
    void init(){
        q1 = new Question ("TestQ1", "TestA1");
        out = new JavaQuestionService();
        out.add(q1.getQuestion(), q1.getAnswer());
    }

    @Test
    void add() {
        assertThat(out.getAll().size()).isEqualTo(1);

        Collection <Question>  questions = new HashSet<>();
        questions.add(q1);
        assertThat(out.getAll()).containsExactlyInAnyOrder(q1);

        Question q2 = new Question("TestQ2", "TestA2");
        assertThat(out.add("TestQ2", "TestA2")).isEqualTo(q2);
        assertThat(out.getAll().size()).isEqualTo(2);

    }

    @Test
    void find() {
        assertThat(out.find("TestQ1")).isEqualTo(q1);
        assertThatExceptionOfType(QuestionNotFoundExceptions.class)
                .isThrownBy(() ->out.find("TestQ99"));
    }

    @Test
    void remove() {
        assertThat(out.getAll().size()).isEqualTo(1);

        assertThat(out.remove("TestQ1")).isEqualTo(q1);
        assertThatExceptionOfType(QuestionNotFoundExceptions.class)
                .isThrownBy(() ->out.remove("TestQ1"));

        assertThat(out.getAll().size()).isEqualTo(0);

    }

    @Test
    void getAll() {
        assertThat(out.getAll().size()).isEqualTo(1);
    }

    @Test
    void getRandomQuestion() {
        assertThat(out.getRandomQuestion()).isEqualTo(q1);
    }
}