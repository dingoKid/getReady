package getready.service;

import java.util.HashSet;
import java.util.List;
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
		List<Long> questionIds = questionRepository.getIds();
		Random random = new Random();
		Long randomId = random.longs( 1, questionIds.size() + 1 ).findFirst().getAsLong();
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
			System.out.println("bent");
			Question questionInDb = existingQuestion.get();
			questionInDb.setAnswer(question.getAnswer());
			questionInDb.setLabels(question.getLabels());
			questionInDb.setInfo(question.getInfo());
//			questionRepository.save(questionInDb);
		} else 
			questionRepository.save(question);
	}
	
	public Question findByLabels(List<String> labelNames) {
		Specification<Question> spec = Specification.where(null);
		
		for (String labelName : labelNames) {
			Optional<Label> label = labelRepository.findByName(labelName);
			if(label.isPresent()) 
				spec = spec.or(QuestionSpecifications.withLabel(label.get()));
		}
		
		List<Question> questionsWithCurrentLabels = questionRepository.findAll(spec);
		return getRandomQuestionFromList(questionsWithCurrentLabels);
	}
	
	public Question getRandomQuestionFromList(List<Question> list) {
		Random random = new Random();
		int randomIndex = random.ints( 0, list.size() ).findFirst().getAsInt();
		return list.get(randomIndex);
	}
	
}
