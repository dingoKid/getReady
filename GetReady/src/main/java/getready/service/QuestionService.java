package getready.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Question getRandomQuestion() {
		List<Long> questionIds = questionRepository.getIds();
		Random random = new Random();
		Long randomId = random.longs( 1, questionIds.size() + 1 ).findFirst().getAsLong();
		return questionRepository.findById(randomId).get();
	}
	
	public Question getQuestionByLabel(String labelName) {
		Label label = labelRepository.findByName(labelName).orElseThrow( () -> new NoSuchElementException("Label not exist: " + labelName) );
		List<Question> questionsWithLabel = questionRepository.findByLabels(label);
		Random random = new Random();
		int randomIndex = random.ints( 0, questionsWithLabel.size() ).findFirst().getAsInt();
		return questionsWithLabel.get(randomIndex);
	}

}
