package hw.skypro.service;

import hw.skypro.model.Question;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


public interface QuestionService {

    Question add(String question, String answer);

    Question find(String question);

    Question remove(String question);

    Collection<Question> getAll();

    Question getRandomQuestion();

}


