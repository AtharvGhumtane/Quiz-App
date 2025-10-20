package com.Atharv.quiz.service.service;

import com.Atharv.quiz.service.dao.QuizDao;
import com.Atharv.quiz.service.feign.QuizInterface;
import com.Atharv.quiz.service.model.QuestionWrapper;
import com.Atharv.quiz.service.model.Quiz;
import com.Atharv.quiz.service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));

        List<Integer> questionIds = quiz.getQuestionIds();

        ResponseEntity<List<QuestionWrapper>> response = quizInterface.getQuestionsFromId(questionIds);

        List<QuestionWrapper> wrappers = response.getBody();

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;

    }
}
