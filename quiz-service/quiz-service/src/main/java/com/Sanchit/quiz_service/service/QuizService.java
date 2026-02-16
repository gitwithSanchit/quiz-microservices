package com.Sanchit.quiz_service.service;

import com.Sanchit.quiz_service.dao.QuizDao;
import com.Sanchit.quiz_service.feign.QuizInterface;
import com.Sanchit.quiz_service.model.QuestionWrapper;
import com.Sanchit.quiz_service.model.Quiz;
import com.Sanchit.quiz_service.model.Response;
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

        System.out.println("=== Creating Quiz ===");
        System.out.println("Category: " + category);
        System.out.println("Number of Questions: " + numQ);
        System.out.println("Title: " + title);

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

        System.out.println("Questions received: " + questions);
        System.out.println("Questions size: " + (questions != null ? questions.size() : "null"));

        if (questions == null || questions.isEmpty()) {
            return new ResponseEntity<>("Failed: No questions found for category: " + category, HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        Quiz savedQuiz = quizDao.save(quiz);

        System.out.println("Quiz saved with ID: " + savedQuiz.getId());
        System.out.println("Quiz question IDs: " + savedQuiz.getQuestionIds());

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}