package getready.web;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import getready.dto.QuestionDto;
import getready.mapper.QuestionMapper;
import getready.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
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
		
//	@GetMapping("/{label}")
//	public QuestionDto getQuestionByLabel(@PathVariable String label) {
//		try {
//			QuestionDto questionDto = questionMapper.questionToDto(questionService.getRandomQuestionByLabel(label));
//			return questionDto;
//		} catch (NoSuchElementException | IllegalArgumentException e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Database is empty or label not exist!");
//		}
//	}
	
	@PostMapping("/save")
	public void saveQuestion(@RequestBody @Valid QuestionDto question) {
		try {
			questionService.saveQuestion(question);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Label not exist!");
		}
	}
	
	@GetMapping("/search/{word}")
	public List<QuestionDto> searchByQuestion(@PathVariable String word) {
		return questionMapper.questionsToDtos(questionService.findByQuestion(word));
	}
	
	
	@PostMapping("/search")
	public List<QuestionDto> searchByLabels(@RequestBody List<String> labels) {
		if(labels.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty label list!");
		return questionMapper.questionsToDtos(questionService.getByLabels(labels));
	}
}
