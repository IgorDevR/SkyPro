package hw.skypro.service;

import hw.skypro.exceptions.RequestExceedsNumberOfDataException;
import hw.skypro.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    QuestionService service;

    public ExaminerServiceImpl(QuestionService service) {
        this.service = service;
    }
    @Override
    public Collection<Question> getQuestions(int amount) {

        if(service.getAll().size() < amount){
            throw new RequestExceedsNumberOfDataException();
        }
        Set<Question> questions = new HashSet<>();
       while (questions.size() < amount){
            questions.add(service.getRandomQuestion());
        }

        return questions;
    }
}
