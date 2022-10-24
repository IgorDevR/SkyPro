package hw.skypro.controller;

import hw.skypro.model.Question;
import hw.skypro.service.ExaminerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class ExamController {

    public final ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Collection<Question> getQuestions(@RequestParam(value = "amount") int amount) {
        return service.getQuestions(amount);
    }

}
