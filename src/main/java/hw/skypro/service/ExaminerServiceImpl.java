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

    Random random;
    @Override
    public Collection<Question> getQuestions(int amount) {

        if(service.getAll().size() < amount){
            throw new RequestExceedsNumberOfDataException();
        }

        Set<Question> questions = new HashSet<>();

        random = new Random(amount + 1);

        int i = 0;
        int randNum = random.nextInt();
        for (int j = questions.size(); j < amount; j++) {

            if(i == randNum){
                var question =  service.getRandomQuestion();
                service.add(question.getQuestion(),question.getAnswer());
                 i = 0;
                 randNum = random.nextInt();
            }
            i++;
        }
        return questions;
    }
}
