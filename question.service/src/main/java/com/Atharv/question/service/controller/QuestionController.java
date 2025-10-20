package com.Atharv.question.service.controller;

import com.Atharv.question.service.model.Question;
import com.Atharv.question.service.model.QuestionWrapper;
import com.Atharv.question.service.model.Response;
import com.Atharv.question.service.service.QuestionService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions().getBody();
    }


    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getAllQuestionsByCategory(category).getBody();
    }



    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }



    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }



    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>>  getQuestionsFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }


}

