package getready.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
	
	@Transactional
	public void addLabels() {
		labelRepository.save(new Label("OOP"));
		labelRepository.save(new Label("annotaion"));
		labelRepository.save(new Label("java"));
		labelRepository.save(new Label("spring"));
		labelRepository.save(new Label("web#"));
	}
	
	@Transactional
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
		
		Question q2 = new Question();
		ask = "sss";
		answer = "kkk";
		List<Label> l2 = new ArrayList<>();
		l2.add(labelRepository.findByName("java").get());
		l2.add(labelRepository.findByName("spring").get());
		l2.add(labelRepository.findByName("OOP").get());
		q2.setAnswer(answer);
		q2.setQuestion(ask);
		q2.setLabels(l2);
		
		questionRepository.save(q2);
		
		
	}

}
