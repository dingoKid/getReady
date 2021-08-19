package getready.web;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import getready.dto.QuestionDto;
import getready.mapper.QuestionMapper;
import getready.service.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@GetMapping
	public QuestionDto getQuestion() {
		try {
			QuestionDto questionDto = questionMapper.questionToDto(questionService.getRandomQuestion());
			return questionDto;
		} catch (NoSuchElementException | IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Database is empty!");
		}
	}
	
	@GetMapping("/{label}")
	public QuestionDto getQuestionByLabel(@PathVariable String label) {
		try {
			QuestionDto questionDto = questionMapper.questionToDto(questionService.getQuestionByLabel(label));
			return questionDto;
		} catch (NoSuchElementException | IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Database is empty!");
		}
	}
	
}
