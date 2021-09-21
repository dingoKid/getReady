package getready.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import getready.dto.QuestionDto;
import getready.mapper.QuestionMapper;
import getready.model.Label;
import getready.model.Question;
import getready.repository.LabelRepository;
import getready.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	QuestionMapper questionMapper;
	
	public Question getRandomQuestion() {
		int questionsInDb = questionRepository.getNumberOfQuestions();
		Random random = new Random();
		Long randomId = random.longs( 1, questionsInDb + 1 ).findFirst().getAsLong();
		return questionRepository.findById(randomId).get();
	}
	
	public List<Question> findByContainingWord(String word) {
		return questionRepository.findByQuestionContainingIgnoreCase(word);
	}
	
	@Transactional
	public void saveQuestion(QuestionDto questionDto) {
		Set<Label> labels = new HashSet<>();
		
		for (String labelName : questionDto.getLabels()) {
			labels.add(labelRepository.findByName(labelName).get());
		}
		
		Question question = questionMapper.dtoToQuestion(questionDto);
		question.setLabels(labels);		
		Optional<Question> existingQuestion = questionRepository.findByQuestion(questionDto.getQuestion());
		if(existingQuestion.isPresent()) {
			Question questionInDb = existingQuestion.get();
			questionInDb.setAnswer(question.getAnswer());
			questionInDb.setLabels(question.getLabels());
			questionInDb.setInfo(question.getInfo());
			System.out.println("Modified");
		} else {
			questionRepository.save(question);
			System.out.println("Saved");
		}
	}
	
	public Question findByLabels(List<String> labelNames) {
		Specification<Question> spec = Specification.where(null);
		
		for (String labelName : labelNames) {
			Optional<Label> label = labelRepository.findByName(labelName);
			if(label.isPresent()) 
				spec = spec.or(QuestionSpecifications.withLabel(label.get()));
		}
		
		List<Question> questionsWithCurrentLabels = questionRepository.findAll(spec);
		if(questionsWithCurrentLabels.isEmpty())
			throw new NoSuchElementException();
		return getRandomQuestionFromList(questionsWithCurrentLabels);
	}
	
	public Question getRandomQuestionFromList(List<Question> list) {
		Random random = new Random();
		int randomIndex = random.ints( 0, list.size() ).findFirst().getAsInt();
		return list.get(randomIndex);
	}
	
	public void deleteQuestion(String question) {
		Optional<Question> questionOpt = questionRepository.findByQuestion(question);
		if(questionOpt.isPresent()) {			
			Question questionInDb = questionOpt.get();
			questionRepository.delete(questionInDb);
		}
	}
	
}
