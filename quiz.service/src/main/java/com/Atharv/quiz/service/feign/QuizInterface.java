package com.Atharv.quiz.service.feign;

import com.Atharv.quiz.service.model.QuestionWrapper;
import com.Atharv.quiz.service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "question-service") // Eureka will resolve this
public interface QuizInterface {

    @GetMapping("/question/generate") // Add leading slash
    ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam("categoryName") String categoryName,
            @RequestParam("numQuestions") Integer numQuestions
    );

    @PostMapping("/question/getQuestions") // Leading slash
    ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(
            @RequestBody List<Integer> questionIds
    );

    @PostMapping("/question/getScore") // Leading slash
    ResponseEntity<Integer> getScore(
            @RequestBody List<Response> responses
    );
}
