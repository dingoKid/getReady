package getready.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import getready.model.Label;
import getready.model.Question;
import getready.repository.LabelRepository;
import getready.repository.QuestionRepository;

@Service
public class InitDbService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	LabelRepository labelRepository;
	
	public void addLabels() {
		labelRepository.save(new Label("OOP"));
		labelRepository.save(new Label("annotaion"));
		labelRepository.save(new Label("java"));
		labelRepository.save(new Label("spring"));
		labelRepository.save(new Label("web"));
	}
	
	public void addQuestions() {
		Question question1 = new Question();
		
		String ask = "What are the solid principles?";
		String answer = "SRP, OCP, LSP, ISP, DIP";
		List<Label> labels = new ArrayList<>();
		labels.add(labelRepository.findByName("OOP").get());		
		question1.setQuestion(ask);
		question1.setAnswer(answer);
		question1.setLabels(labels);	
		
		questionRepository.save(question1);
		
	}

}
