package hw.skypro.controller;

import hw.skypro.model.Question;
import hw.skypro.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    public final QuestionService service;


    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.remove(question);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Collection<Question> getQuestions() {
        return service.getAll();
    }
}
