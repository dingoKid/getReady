package getready.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Question getRandomQuestionByLabel(String labelName) {
		Label label = labelRepository.findByName(labelName).get();
		List<Question> questionsWithLabel = questionRepository.findByLabels(label);
		Random random = new Random();
		int randomIndex = random.ints( 0, questionsWithLabel.size() ).findFirst().getAsInt();
		return questionsWithLabel.get(randomIndex);
	}
	
	@Transactional
	public void saveQuestion(QuestionDto questionDto) {
		List<Label> labels = new ArrayList<>();
		
		for (String labelName : questionDto.getLabels()) {
			labels.add(labelRepository.findByName(labelName).get());
		}
		
		Question question = questionMapper.dtoToQuestion(questionDto);
		question.setLabels(labels);		
		questionRepository.save(question);
	}

}
