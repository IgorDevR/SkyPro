package hw.skypro.service;

import hw.skypro.exceptions.QuestionNotFoundExceptions;
import hw.skypro.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JavaQuestionService implements QuestionService {

    Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question find(String question) {

        for (Question q : questions) {
            if (q.getQuestion().equals(question)) {
                return q;
            }
        }
        throw new QuestionNotFoundExceptions();
    }


    @Override
    public Question remove(String question) {
        for (Question q : questions) {
            if (q.getQuestion().equals(question)) {
                questions.remove(q);
                return q;
            }
        }
        throw new QuestionNotFoundExceptions();
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {

        Random random = new Random();
        int randNum = random.nextInt(questions.size());
        return questions.stream().collect(Collectors.toList()).get(randNum);

    }
}
